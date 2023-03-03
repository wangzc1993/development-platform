package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class ConditionTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        return engine.execute(context, getFirstChildTag());
    }

    @Override
    public void check() throws TagCheckException {

    }

}
