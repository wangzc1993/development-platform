package org.wangzc.development.platform.model.api.engine.tag;

import org.nutz.lang.Strings;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class ValueTag extends AbstractTag {

    private static final String TYPE_NAME_VALUE = "name-value";
    private static final String TYPE_NAME_REF = "name-ref";
    private static final String TYPE_NAME_CODE = "name-code";
    private static final String TYPE_VALUE = "value";
    private static final String TYPE_REF = "ref";
    private static final String TYPE_CODE = "code";

    private String type;

    /**
     * 当name不为空时表示正在定义变量或对已存在的变量进行赋值
     */
    private String name;

    /**
     * 当ref不为空时表示正在对ref表示的变量名字进行取值
     */
    private String ref;

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        if (TYPE_NAME_VALUE.equals(type)) {
            context.setArg(name, getTextContext());
            return null;
        }
        if (TYPE_NAME_REF.equals(type)) {
            context.setArg(name, context.getArg(ref));
            return null;
        }
        if (TYPE_NAME_CODE.equals(type)) {
            context.setArg(name, engine.execute(context, getFirstChildTag()));
            return null;
        }
        if (TYPE_VALUE.equals(type)) {
            return getTextContext();
        }
        if (TYPE_REF.equals(type)) {
            return context.getArg(ref);
        }
        if (TYPE_CODE.equals(type)) {
            return engine.execute(context, getFirstChildTag());
        }
        return null;
    }

    @Override
    public void check() throws TagCheckException {
        if (Strings.isBlank(type)) {
            throw new TagCheckException(getId(), "缺少属性[变量类型]");
        }
        if (TYPE_NAME_VALUE.equals(type)) {
            if (Strings.isBlank(name)) {
                throw new TagCheckException(getId(), "缺少属性[变量名称]");
            }
            if (0 != getChildTags().size()) {
                throw new TagCheckException(getId(), "不可包含代码");
            }
            return;
        }
        if (TYPE_NAME_REF.equals(type)) {
            if (Strings.isBlank(name)) {
                throw new TagCheckException(getId(), "缺少属性[变量名称]");
            }
            if (Strings.isBlank(ref)) {
                throw new TagCheckException(getId(), "缺少属性[引用变量名称]");
            }
            return;
        }
        if (TYPE_NAME_CODE.equals(type)) {
            if (Strings.isBlank(name)) {
                throw new TagCheckException(getId(), "缺少属性[变量名称]");
            }
            if (1 != getChildTags().size()) {
                throw new TagCheckException(getId(), "必须包含一个代码");
            }
            return;
        }
        if (TYPE_VALUE.equals(type)) {
            if (0 != getChildTags().size()) {
                throw new TagCheckException(getId(), "不可包含代码");
            }
            return;
        }
        if (TYPE_REF.equals(type)) {
            if (Strings.isBlank(ref)) {
                throw new TagCheckException(getId(), "缺少属性[引用变量名称]");
            }
            return;
        }
        if (TYPE_CODE.equals(type)) {
            if (Strings.isBlank(ref)) {
                throw new TagCheckException(getId(), "必须包含一个代码");
            }
            return;
        }
        throw new TagCheckException(getId(), "属性[变量类型]错误");
    }

}
