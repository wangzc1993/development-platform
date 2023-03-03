package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class ParametersTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        return engine.execute(context, getChildTags());
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() != ApiUtils.getTagListByType(getChildTags(), ParameterTag.class).size()) {
            throw new TagCheckException(getId(), "只能包含参数代码");
        }
    }

}
