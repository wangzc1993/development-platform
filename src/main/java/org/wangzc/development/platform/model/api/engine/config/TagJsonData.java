package org.wangzc.development.platform.model.api.engine.config;

import java.util.Arrays;
import java.util.List;

import org.nutz.lang.util.NutMap;

public class TagJsonData {

    private String tag;

    private NutMap attributes;

    private List<TagJsonData> childs;

    private String textContext;

    public TagJsonData(ApiTagEnum tagEnum, TagJsonData... childs) {
        this.tag = tagEnum.getTag();
        this.attributes = new NutMap();
        this.childs = Arrays.asList(childs);
        this.textContext = "";
        this.attributes.put("id", "");
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public NutMap getAttributes() {
        return attributes;
    }

    public void setAttributes(NutMap attributes) {
        this.attributes = attributes;
    }

    public List<TagJsonData> getChilds() {
        return childs;
    }

    public void setChilds(List<TagJsonData> childs) {
        this.childs = childs;
    }

    public String getTextContext() {
        return textContext;
    }

    public void setTextContext(String textContext) {
        this.textContext = textContext;
    }

}
