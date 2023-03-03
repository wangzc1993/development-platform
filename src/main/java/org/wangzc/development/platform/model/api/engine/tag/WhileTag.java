package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.BreakException;
import org.wangzc.development.platform.model.api.engine.exception.ContinueException;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class WhileTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        ConditionTag conditionTag = ApiUtils.getTagByType(getChildTags(), ConditionTag.class);
        DoTag doTag = ApiUtils.getTagByType(getChildTags(), DoTag.class);
        out: while (ApiUtils.isTrue(engine.execute(context, conditionTag))) {
            try {
                engine.execute(context, doTag);
            } catch (BreakException e) {
                break out;
            } catch (ContinueException e) {
                continue out;
            }
        }
        return null;
    }

    @Override
    public void check() throws TagCheckException {
        if (null == ApiUtils.getTagByType(getChildTags(), ConditionTag.class)) {
            throw new TagCheckException(getId(), "缺少判断条件代码");
        }
        if (null == ApiUtils.getTagByType(getChildTags(), ConditionTag.class)) {
            throw new TagCheckException(getId(), "缺少执行代码");
        }
    }

}
