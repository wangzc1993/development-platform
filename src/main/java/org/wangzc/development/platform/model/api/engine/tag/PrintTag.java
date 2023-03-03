package org.wangzc.development.platform.model.api.engine.tag;

import java.util.List;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class PrintTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        if (hasChild()) {
            List<Object> list = engine.execute(context, getChildTags());
            StringBuilder sb = new StringBuilder();
            for (Object l : list) {
                sb.append(l).append(", ");
            }
            engine.println(sb);
        } else {
            engine.println(getTextContext());
        }
        return null;
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() < 1) {
            throw new TagCheckException(getId(), "至少包含一个代码");
        }
    }

}
