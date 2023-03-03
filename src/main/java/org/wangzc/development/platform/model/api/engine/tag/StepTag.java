package org.wangzc.development.platform.model.api.engine.tag;

import java.math.BigDecimal;

import org.nutz.lang.Strings;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class StepTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        return new BigDecimal(getTextContext());
    }

    @Override
    public void check() throws TagCheckException {
        if (Strings.isBlank(getTextContext())) {
            throw new TagCheckException(getId(), "缺少递进值");
        }
        if (!Strings.isNumber(getTextContext())) {
            throw new TagCheckException(getId(), "递进值只能为数字");
        }
    }

}
