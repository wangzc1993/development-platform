package org.wangzc.development.platform.model.api.engine.tag;

import org.nutz.lang.util.NutMap;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class MapTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        NutMap map = new NutMap();
        for (Object res : engine.execute(context, getChildTags())) {
            map.putAll((NutMap) res);
        }
        return map;
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() != ApiUtils.getTagListByType(getChildTags(), MapValueTag.class).size()) {
            throw new TagCheckException(getId(), "只能包含键值代码");
        }
    }

}
