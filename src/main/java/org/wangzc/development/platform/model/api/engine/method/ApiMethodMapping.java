package org.wangzc.development.platform.model.api.engine.method;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.castor.Castors;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Files;
import org.nutz.lang.LoopException;
import org.nutz.lang.Xmls;
import org.nutz.lang.stream.StringInputStream;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wangzc.development.platform.model.api.engine.config.ApiArgTypeEnum;
import org.wangzc.development.platform.model.api.engine.exception.MethodException;

public class ApiMethodMapping {

    private static final Map<Class<?>, Object> SIGNAL_MAP = new HashMap<>();

    private static final Map<String, ApiMethodMappingEntity> METHOD_MAP = new HashMap<>();

    public static void init(String methodMappingPath) {
        List<File> files = loadFiles(new File(methodMappingPath));
        for (File f : files) {
            paseXml(Files.read(f));
        }
    }

    public static Object invoke(String name, Object... args) throws MethodException {
        ApiMethodMappingEntity entity = METHOD_MAP.get(name);
        if (null == entity) {
            throw new MethodException("方法不存在: " + name);
        }
        Class<?>[] targetParametersType = entity.getParametersType();
        if (targetParametersType.length != args.length) {
            throw new MethodException("传入参数与方法参数不一致");
        }
        Object[] parameters = new Object[targetParametersType.length];
        Castors me = Castors.me();
        for (int i = 0; i < targetParametersType.length; i++) {
            parameters[i] = me.castTo(args[i], targetParametersType[i]);
        }
        if (Modifier.isStatic(entity.getMethod().getModifiers())) {
            return invoke(null, entity, parameters);
        }
        if (ApiMethodScopeEnum.SIGNAL == entity.getScope()) {
            Object object = SIGNAL_MAP.get(entity.getClazz());
            if (null == object) {
                try {
                    object = entity.getClazz().getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    throw new MethodException("方法类构建失败", e);
                }
                SIGNAL_MAP.put(entity.getClazz(), object);
            }
            return invoke(object, entity, parameters);
        }
        throw new MethodException("方法作用域暂不支持");
    }

    private static Object invoke(Object obj, ApiMethodMappingEntity entity, Object... params) throws MethodException {
        try {
            Object invoke = entity.getMethod().invoke(obj, params);
            return Castors.me().castTo(invoke, entity.getTargetReturnType().getType());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new MethodException("方法执行失败", e);
        }
    }

    private static List<File> loadFiles(File path) {
        List<File> list = new ArrayList<>();
        File[] files = Files.files(path, null);
        for (File f : files) {
            if (f.isFile()) {
                list.add(f);
            }
            if (f.isDirectory()) {
                list.addAll(loadFiles(f));
            }
        }
        return list;
    }

    private static void paseXml(String xml) {
        Document doc = Xmls.xml(new StringInputStream(xml));
        Class<?> clazz = null;
        String string = Xmls.get(doc.getDocumentElement(), "class");
        try {
            clazz = Class.forName(string);
        } catch (ClassNotFoundException | DOMException e) {
            throw new LoopException(e);
        }
        Class<?> finalClazz = clazz;
        Xmls.eachChildren(doc.getDocumentElement(), (int index, Element ele, int length) -> {
            System.out.println("------------------- " + ele.getTagName());
            String description = null;
            if ("description".equals(ele.getTagName())) {
                description = ele.getTextContent();
            }
            if ("methods".equals(ele.getTagName())) {
                Xmls.eachChildren(ele, (int i2, Element e2, int l2) -> {
                    if (!"method".equals(e2.getTagName())) {
                        throw new ContinueLoop();
                    }
                    try {
                        parseMethod(e2, finalClazz);
                    } catch (MethodException e) {
                        throw new LoopException(e);
                    }

                });
            }
        });
    }

    private static void parseMethod(Element methodElement, Class<?> clazz) throws MethodException {
        ApiMethodMappingEntity entity = new ApiMethodMappingEntity();
        entity.setClazz(clazz);
        Xmls.eachChildren(methodElement, (i, ele, index) -> {
            System.out.println("======================= " + ele.getTagName());
            if ("name".equals(ele.getTagName())) {
                entity.setName(ele.getTextContent());
            }
            if ("description".equals(ele.getTagName())) {
                entity.setDescription(ele.getTextContent());
            }
            if ("methodName".equals(ele.getTagName())) {
                entity.setMethodName(ele.getTextContent());
            }
            if ("parameters".equals(ele.getTagName())) {
                parseMethodParametersType(ele, entity);
            }
            if ("scopt".equals(ele.getTagName())) {
                entity.setScope(ApiMethodScopeEnum.valueOf(ele.getTextContent()));
            }
            if ("return".equals(ele.getTagName())) {
                try {
                    entity.setReturnType(Class.forName(ele.getAttribute("type")));
                } catch (ClassNotFoundException e) {
                    throw new LoopException(e);
                }
                entity.setTargetReturnType(ApiArgTypeEnum.valueOf(ele.getTextContent()));
            }
        });
        try {
            entity.setMethod(clazz.getDeclaredMethod(entity.getMethodName(), entity.getParametersType()));
        } catch (NoSuchMethodException | SecurityException e) {
            throw new MethodException(e.getMessage(), e);
        }
        METHOD_MAP.put(entity.getName(), entity);
    }

    private static void parseMethodParametersType(Element parametersElement, ApiMethodMappingEntity entity) {
        List<Class<?>> parameters = new ArrayList<>();
        List<ApiArgTypeEnum> targetParameters = new ArrayList<>();
        Xmls.eachChildren(parametersElement, (i, ele, index) -> {
            if (!"parameter".equals(ele.getTagName())) {
                throw new ContinueLoop();
            }
            try {
                parameters.add(Class.forName(ele.getAttribute("type")));
            } catch (ClassNotFoundException e) {
                throw new LoopException(e);
            }
            targetParameters.add(ApiArgTypeEnum.valueOf(ele.getTextContent()));
        });
        entity.setParametersType(parameters.toArray(new Class[0]));
        entity.setTargetParametersType(targetParameters.toArray(new ApiArgTypeEnum[0]));
    }
}
