package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class DoTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        engine.execute(context, getChildTags());
        return null;
    }

    @Override
    public void check() throws TagCheckException {

    }

}
