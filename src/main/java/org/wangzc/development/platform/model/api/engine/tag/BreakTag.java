package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.BreakException;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class BreakTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        throw new BreakException();
    }

    @Override
    public void check() throws TagCheckException {
        
    }

}
