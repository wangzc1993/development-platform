package org.wangzc.development.platform.model.api.engine.tag;

import org.nutz.lang.Strings;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class StartTag extends AbstractTag {

    private String name;

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        Object obj = engine.execute(context, getFirstChildTag());
        context.setArg(name, obj);
        return name;
    }

    @Override
    public void check() throws TagCheckException {
        if (Strings.isBlank(name)) {
            throw new TagCheckException(getId(), "缺少属性[开始变量名称]");
        }
        if (1 != getChildTags().size()) {
            throw new TagCheckException(getId(), "只能包含一个代码");
        }
    }

}
