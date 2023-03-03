package org.wangzc.development.platform.model.api.engine.tag;

import java.math.BigDecimal;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class LessOrEqualTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        Object o1 = engine.execute(context, getFirstChildTag());
        Object o2 = engine.execute(context, getChildTags().get(1));
        BigDecimal b1 = new BigDecimal(String.valueOf(o1));
        BigDecimal b2 = new BigDecimal(String.valueOf(o2));
        return b1.doubleValue() <= b2.doubleValue();
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() != 2) {
            throw new TagCheckException(getId(), "必须包含两个代码");
        }
    }

}
