package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class IfTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        ConditionTag conditionTag = ApiUtils.getTagByType(getChildTags(), ConditionTag.class);
        DoTag doTag = ApiUtils.getTagByType(getChildTags(), DoTag.class);
        ElseTag elseTag = ApiUtils.getTagByType(getChildTags(), ElseTag.class);
        Object execute = engine.execute(context, conditionTag);
        if (ApiUtils.isTrue(execute)) {
            engine.execute(context, doTag);
            return null;
        }
        if (null != elseTag) {
            engine.execute(context, elseTag);
        }
        return null;
    }

    @Override
    public void check() throws TagCheckException {
        if (null == ApiUtils.getTagByType(getChildTags(), ConditionTag.class)) {
            throw new TagCheckException(getId(), "缺少条件代码");
        }
        if (null == ApiUtils.getTagByType(getChildTags(), DoTag.class)) {
            throw new TagCheckException(getId(), "缺少执行代码");
        }
    }
}
