package org.wangzc.development.platform.model.api.engine.tag;

import java.math.BigDecimal;

import org.nutz.castor.Castors;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.BreakException;
import org.wangzc.development.platform.model.api.engine.exception.ContinueException;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class ForTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        StartTag startTag = ApiUtils.getTagByType(getChildTags(), StartTag.class);
        ConditionTag conditionTag = ApiUtils.getTagByType(getChildTags(), ConditionTag.class);
        StepTag stepTag = ApiUtils.getTagByType(getChildTags(), StepTag.class);
        DoTag doTag = ApiUtils.getTagByType(getChildTags(), DoTag.class);
        String startValueName = String.valueOf(engine.execute(context, startTag));
        BigDecimal stepValue = Castors.me().castTo(engine.execute(context, stepTag), BigDecimal.class);
        while (ApiUtils.isTrue(engine.execute(context, conditionTag))) {
            try {
                engine.execute(context, doTag);
            } catch (BreakException e) {
                break;
            } catch (ContinueException e) {
                continue;
            } finally {
                BigDecimal val = Castors.me().castTo(context.getArg(startValueName), BigDecimal.class).add(stepValue);
                context.setArg(startValueName, val);
            }
        }
        return null;
    }

    @Override
    public void check() throws TagCheckException {
        if (null == ApiUtils.getTagByType(getChildTags(), StartTag.class)) {
            throw new TagCheckException(getId(), "缺少开始变量代码");
        }
        if (null == ApiUtils.getTagByType(getChildTags(), ConditionTag.class)) {
            throw new TagCheckException(getId(), "缺少循环条件代码");
        }
        if (null == ApiUtils.getTagByType(getChildTags(), StepTag.class)) {
            throw new TagCheckException(getId(), "缺少递进代码");
        }
        if (null == ApiUtils.getTagByType(getChildTags(), DoTag.class)) {
            throw new TagCheckException(getId(), "缺少执行代码");
        }
    }

}
