package org.wangzc.development.platform.model.api.engine.tag;

import java.util.List;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class OrTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        List<Object> ls = engine.execute(context, getChildTags());
        boolean or = true;
        for (int i = 1; i < ls.size(); i++) {
            or = ApiUtils.isTrue(ls.get(i - 1)) || ApiUtils.isTrue(ls.get(i));
        }
        return or;
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() < 2) {
            throw new TagCheckException(getId(), "至少包含两个代码");
        }
    }
}
