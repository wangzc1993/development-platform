package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class NotTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        return !ApiUtils.isTrue(engine.execute(context, getFirstChildTag()));
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() != 1) {
            throw new TagCheckException(getId(), "只能包含一个代码");
        }
    }

}
