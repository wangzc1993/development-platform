package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class TargetTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        return engine.execute(context, getFirstChildTag());
    }

    @Override
    public void check() throws TagCheckException {
        if (1 != getChildTags().size()) {
            throw new TagCheckException(getId(), "必须包含一个代码");
        }
    }

}
