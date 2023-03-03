package org.wangzc.development.platform.model.api.engine.tag;

import org.nutz.lang.Strings;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class ParameterTag extends AbstractTag {

    public static final String TYPE_DEF = "def";
    public static final String TYPE_REF = "ref";

    private String type;

    private String name;

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        if (TYPE_DEF.equals(type)) {
            return name;
        }
        if (TYPE_REF.equals(type)) {
            return engine.execute(context, getFirstChildTag());
        }
        return null;
    }

    @Override
    public void check() throws TagCheckException {
        if (Strings.isBlank(type)) {
            throw new TagCheckException(getId(), "缺少属性[参数类型]");
        }
        if (!TYPE_DEF.equals(type) && !TYPE_REF.equals(type)) {
            throw new TagCheckException(getId(), "属性[参数类型]错误");
        }
        if (TYPE_DEF.equals(type) && Strings.isBlank(name)) {
            throw new TagCheckException(getId(), "缺少属性[参数名称]");
        }
        if (TYPE_REF.equals(type) && 1 != getChildTags().size()) {
            throw new TagCheckException(getId(), "必须包含一个代码");
        }
    }

    public String getType() {
        return type;
    }
}
