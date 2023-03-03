package org.wangzc.development.platform.model.api.engine.tag;

import java.math.BigDecimal;
import java.util.List;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class SubtractTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        List<Object> res = engine.execute(context, getChildTags());
        BigDecimal b = new BigDecimal(String.valueOf(res.get(0)));
        for (int i = 1; i < res.size(); i++) {
            String s = String.valueOf(res.get(i));
            b = b.subtract(new BigDecimal(s));
        }
        return b;
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() < 2) {
            throw new TagCheckException(getId(), "至少包含两个代码");
        }
    }

}
