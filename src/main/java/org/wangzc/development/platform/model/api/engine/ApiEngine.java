package org.wangzc.development.platform.model.api.engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.castor.Castors;
import org.nutz.castor.FailToCastObjectException;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.LoopException;
import org.nutz.lang.Xmls;
import org.nutz.lang.stream.StringInputStream;
import org.nutz.lang.util.NutMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.wangzc.development.platform.model.api.engine.config.ApiConfig;
import org.wangzc.development.platform.model.api.engine.exception.ReturnException;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;
import org.wangzc.development.platform.model.api.engine.method.ApiMethodMapping;
import org.wangzc.development.platform.model.api.engine.tag.AbstractTag;

public class ApiEngine {

    private AbstractTag rootTag;

    private ApiContext apiContext;

    private Map<String, AbstractTag> idTagMap = new HashMap<>();

    public Object execute(ApiContext context, AbstractTag tag) throws TagException {
        if (!ApiConfig.AREA_TAG.getOrDefault(tag.getClass(), false)) {
            return tag.execute(context, this);
        }
        Object obj;
        context.addArgsArea(tag.getId());
        obj = tag.execute(context, this);
        context.removeArgsArea(tag.getId());
        return obj;
    }

    public List<Object> execute(ApiContext context, List<AbstractTag> tag) throws TagException {
        List<Object> objs = new ArrayList<>();
        for (AbstractTag t : tag) {
            objs.add(execute(context, t));
        }
        return objs;
    }

    public void init(String xml) throws TagException {
        apiContext = new ApiContext();
        rootTag = parse(xml);
    }

    public Object execute(NutMap apiParameters) throws TagException {
        apiContext.setApiParameters(apiParameters);
        try {
            execute(apiContext, rootTag);
            return null;
        } catch (ReturnException e) {
            return e.getData();
        } catch (TagException e) {
            throw e;
        } catch (Exception e) {
            throw new TagException(e.getMessage(), e);
        }
    }

    
    public Object execute() throws TagException {
        return execute(new NutMap());
    }


    public void check() throws TagCheckException {
        check(rootTag);
    }

    private void check(AbstractTag tag) throws TagCheckException {
        tag.check0();
        if (idTagMap.containsKey(tag.getId())) {
            throw new TagCheckException(tag.getId(), "ID不可重复");
        }
        for (AbstractTag child : tag.getChildTags()) {
            check(child);
        }
    }

    public List<TagCheckException> checkAll() {
        List<TagCheckException> exs = new ArrayList<>();
        checkAll(rootTag, exs);
        return exs;
    }

    private void checkAll(AbstractTag tag, List<TagCheckException> exs) {
        try {
            tag.check0();
        } catch (TagCheckException e) {
            exs.add(e);
        }
        if (idTagMap.containsKey(tag.getId())) {
            exs.add(new TagCheckException(tag.getId(), "ID不可重复"));
        }
        for (AbstractTag child : tag.getChildTags()) {
            checkAll(child, exs);
        }
    }

    private AbstractTag parse(String xml) throws TagException {
        Document doc = Xmls.xml(new StringInputStream(xml));
        return each(doc.getDocumentElement());
    }

    private AbstractTag each(Element element) throws TagException {
        AbstractTag tag = toTag(element);
        if (Xmls.children(element).size() == 0) {
            tag.setChildTags(new ArrayList<>());
            tag.setTextContext(element.getTextContent());
            return tag;
        }
        List<AbstractTag> list = new ArrayList<>();
        Xmls.eachChildren(element, (int index, Element ele, int length) -> {
            AbstractTag child;
            try {
                child = each(ele);
            } catch (TagException e) {
                throw new LoopException(e);
            }
            child.setParentTag(tag);
            list.add(child);
        });
        tag.setChildTags(list);
        return tag;
    }

    private AbstractTag toTag(Element ele) throws TagException {
        Class<? extends AbstractTag> clazz = ApiConfig.TAG_MAP.get(ele.getTagName());
        if (null == clazz) {
            throw new TagException("未找到标签：" + ele.getTagName());
        }
        AbstractTag newInstance;
        try {
            Constructor<? extends AbstractTag> constructor = clazz.getConstructor();
            newInstance = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new TagException(e.getMessage(), e);
        }
        NamedNodeMap attributes = ele.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node item = attributes.item(i);
            if ("id".equals(item.getNodeName())) {
                newInstance.setId(item.getNodeValue());
                continue;
            }
            Field field;
            try {
                field = clazz.getDeclaredField(item.getNodeName());
            } catch (NoSuchFieldException | SecurityException e) {
                throw new TagException(e.getMessage(), e);
            }
            setFieldValue(newInstance, field, item.getNodeValue());
        }
        return newInstance;
    }

    private void setFieldValue(Object obj, Field field, Object value) throws TagException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            field.set(obj, Castors.me().castTo(value, field.getType()));
        } catch (IllegalArgumentException | IllegalAccessException | FailToCastObjectException e) {
            throw new TagException(e.getMessage(), e);
        }
    }

    public void println(Object log) {
        apiContext.addLog(String.valueOf(log));
        System.out.println(log);
    }

    public ApiContext getApiContext() {
        return apiContext;
    }

    public static void main(String[] args) throws TagException {
        ApiMethodMapping.init("D:\\Project Files\\development-platform\\files\\method-mapping");
        String s = Files.read("D:\\Project Files\\development-platform\\files\\api\\test.xml");
        ApiEngine engine = new ApiEngine();
        engine.init(s);
        try {
            engine.check();
        } catch (TagCheckException e) {
            System.err.println(e.getId());
            throw e;
        }
        Object data = engine.execute(new NutMap("请求参数1", "aaaaa"));
        System.out.println("输出结果：" + Json.toJson(data));
    }
}
