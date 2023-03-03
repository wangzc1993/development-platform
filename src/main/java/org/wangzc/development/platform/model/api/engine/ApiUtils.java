package org.wangzc.development.platform.model.api.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.nutz.lang.Strings;
import org.nutz.lang.Xmls;
import org.nutz.lang.stream.StringInputStream;
import org.nutz.lang.util.NutMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.wangzc.development.platform.model.api.engine.tag.AbstractTag;

public class ApiUtils {

    public static boolean isTrue(Object obj) {
        if (null == obj) {
            return false;
        }
        String s = String.valueOf(obj);
        if ("false".equals(s) || "0".equals(s) || "".equals(s)) {
            return false;
        }
        return true;
    }

    public static <T> T getTagByType(List<AbstractTag> list, Class<T> type) {
        for (AbstractTag tag : list) {
            if (type.isAssignableFrom(tag.getClass())) {
                return (T) tag;
            }
        }
        return null;
    }

    public static <T> List<T> getTagListByType(List<AbstractTag> list, Class<T> type) {
        List<T> ls = new ArrayList<>();
        for (AbstractTag tag : list) {
            if (type.isAssignableFrom(tag.getClass())) {
                ls.add((T) tag);
            }
        }
        return ls;
    }

    public static boolean isEqual(Object o1, Object o2) {
        if (Objects.equals(o1, o2)) {
            return true;
        }
        String s1 = String.valueOf(o1);
        String s2 = String.valueOf(o2);
        return Objects.equals(s1, s2);
    }

    public static JsonTagEntity xmlToJson(String xml) {
        Document doc = Xmls.xml(new StringInputStream(xml));
        return each(doc.getDocumentElement());
    }

    public static String jsonToXml(String json) {
        NutMap map = new NutMap(json);
        return eachJson(map);
    }

    private static String eachJson(NutMap map) {
        String tag = map.getString("tag");
        NutMap attributes = map.getAs("attributes", NutMap.class);
        String textContext = map.getString("textContext");
        List<NutMap> childs = map.getList("childs", NutMap.class);
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tag);
        if (null != attributes) {
            for (Entry<String, Object> entrySet : attributes.entrySet()) {
                sb.append(" ").append(entrySet.getKey()).append("=").append("\"").append(entrySet.getValue())
                        .append("\"");
            }
        }
        sb.append(">");
        sb.append(Strings.isBlank(textContext) ? "" : "<![CDATA[" + textContext + "]]>");
        if (null != childs && childs.size() > 0) {
            for (NutMap c : childs) {
                sb.append(eachJson(c));
            }
        }
        sb.append("</").append(tag).append(">");
        return sb.toString();
    }

    private static JsonTagEntity each(Element element) {
        JsonTagEntity tag = new JsonTagEntity();
        tag.setTag(element.getTagName());
        Map<String, String> attributes = new HashMap<>();
        for (int i = 0; i < element.getAttributes().getLength(); i++) {
            Node item = element.getAttributes().item(i);
            attributes.put(item.getNodeName(), item.getNodeValue());
        }
        tag.setAttributes(attributes);
        if (Xmls.children(element).size() == 0) {
            tag.setChilds(new ArrayList<>());
            tag.setTextContext(element.getTextContent());
            return tag;
        }
        List<JsonTagEntity> list = new ArrayList<>();
        Xmls.eachChildren(element, (int index, Element ele, int length) -> {
            JsonTagEntity child = each(ele);
            list.add(child);
        });
        tag.setChilds(list);
        return tag;
    }

    public static class JsonTagEntity {
        private String tag;
        private Map<String, String> attributes;
        private List<JsonTagEntity> childs;
        private String textContext;

        public String getTag() {
            return tag;
        }

        public void setTag(String tagName) {
            this.tag = tagName;
        }

        public Map<String, String> getAttributes() {
            return attributes;
        }

        public void setAttributes(Map<String, String> attributes) {
            this.attributes = attributes;
        }

        public List<JsonTagEntity> getChilds() {
            return childs;
        }

        public void setChilds(List<JsonTagEntity> childs) {
            this.childs = childs;
        }

        public String getTextContext() {
            return textContext;
        }

        public void setTextContext(String textContent) {
            this.textContext = textContent;
        }

    }
}
