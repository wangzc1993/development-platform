package org.wangzc.development.platform.model.api.engine.tag;

import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class MapValueTag extends AbstractTag {

    private String key;

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        return new NutMap(key, engine.execute(context, getFirstChildTag()));
    }

    @Override
    public void check() throws TagCheckException {
        if (Strings.isBlank(key)) {
            throw new TagCheckException(getId(), "属性[键]不可为空");
        }
        if (1 != getChildTags().size()) {
            throw new TagCheckException(getId(), "必须包含一个代码");
        }
    }

}
