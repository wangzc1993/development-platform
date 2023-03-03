package org.wangzc.development.platform.model.api.engine.tag;

import java.util.List;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class NotEqualTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        List<Object> ls = engine.execute(context, getChildTags());
        for (int i = 1; i < ls.size(); i++) {
            if (ApiUtils.isEqual(ls.get(i - 1), ls.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() < 2) {
            throw new TagCheckException(getId(), "至少包含两个代码");
        }
    }
}
