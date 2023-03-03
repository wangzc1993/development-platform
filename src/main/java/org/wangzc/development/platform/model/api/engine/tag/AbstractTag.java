package org.wangzc.development.platform.model.api.engine.tag;

import java.util.List;

import org.nutz.lang.Strings;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public abstract class AbstractTag {

    /**
     * ID
     */
    private String id;

    /**
     * 标签中的字符串文本
     */
    private String textContext;

    /**
     * 父级节点
     */
    private AbstractTag parentTag;

    /**
     * 子节点数组
     */
    private List<AbstractTag> childTags;

    /**
     * 执行当前节点，不可由节点直接调用，仅允许engine调用
     * 
     * @param context
     * @param engine
     * @return
     */
    public abstract Object execute(ApiContext context, ApiEngine engine) throws TagException;

    public abstract void check() throws TagCheckException;

    public void check0() throws TagCheckException {
        if (Strings.isBlank(id)) {
            throw new TagCheckException(getId(), "缺少属性[ID]");
        }
        check();
    }

    public AbstractTag getFirstChildTag() {
        if (hasChild()) {
            return childTags.get(0);
        }
        return null;
    }

    public boolean hasChild() {
        return !childTags.isEmpty();
    }

    public String getTextContext() {
        return textContext;
    }

    public void setTextContext(String textContext) {
        this.textContext = textContext;
    }

    public List<AbstractTag> getChildTags() {
        return childTags;
    }

    public void setChildTags(List<AbstractTag> childs) {
        this.childTags = childs;
    }

    public AbstractTag getParentTag() {
        return parentTag;
    }

    public void setParentTag(AbstractTag parentTag) {
        this.parentTag = parentTag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
