package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.ReturnException;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class ReturnTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        Object obj;
        if (hasChild()) {
            obj = engine.execute(context, getFirstChildTag());
        } else {
            obj = getTextContext();
        }
        throw new ReturnException(obj);
    }

    @Override
    public void check() throws TagCheckException {
        if (1 != getChildTags().size()) {
            throw new TagCheckException(getId(), "必须包含一个代码");
        }
    }

}
