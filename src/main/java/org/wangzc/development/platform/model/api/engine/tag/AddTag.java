package org.wangzc.development.platform.model.api.engine.tag;

import java.math.BigDecimal;
import java.util.List;

import org.nutz.lang.Strings;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class AddTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        List<Object> res = engine.execute(context, getChildTags());
        boolean isAllNumber = true;
        for (Object o : res) {
            String s = String.valueOf(o);
            if (!Strings.isNumber(s)) {
                isAllNumber = false;
                break;
            }
        }
        if (isAllNumber) {
            BigDecimal b = BigDecimal.ZERO;
            for (Object o : res) {
                String s = String.valueOf(o);
                b = b.add(new BigDecimal(s));
            }
            return b;
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : res) {
            String s = String.valueOf(o);
            sb.append(s);
        }
        return sb.toString();
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() < 2) {
            throw new TagCheckException(getId(), "至少包含两个代码");
        }
    }

}
