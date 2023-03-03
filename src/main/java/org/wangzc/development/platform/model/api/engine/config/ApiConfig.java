package org.wangzc.development.platform.model.api.engine.config;

import java.util.HashMap;
import java.util.Map;

import org.wangzc.development.platform.model.api.engine.tag.AbstractTag;
import org.wangzc.development.platform.model.api.engine.tag.ForTag;
import org.wangzc.development.platform.model.api.engine.tag.ForeachTag;
import org.wangzc.development.platform.model.api.engine.tag.FunctionTag;
import org.wangzc.development.platform.model.api.engine.tag.IfTag;
import org.wangzc.development.platform.model.api.engine.tag.SelectTag;
import org.wangzc.development.platform.model.api.engine.tag.WhileTag;
import org.wangzc.development.platform.model.api.engine.tag.XmlTag;

public class ApiConfig {

    public static final Map<String, Class<? extends AbstractTag>> TAG_MAP = new HashMap<>();

    public static final Map<Class<? extends AbstractTag>, Boolean> AREA_TAG = new HashMap<>();

    static {
        for (ApiTagEnum tag : ApiTagEnum.values()) {
            TAG_MAP.put(tag.getTag(), tag.getType());
        }

        AREA_TAG.put(XmlTag.class, true);
        AREA_TAG.put(IfTag.class, true);
        AREA_TAG.put(WhileTag.class, true);
        AREA_TAG.put(ForeachTag.class, true);
        AREA_TAG.put(ForTag.class, true);
        AREA_TAG.put(SelectTag.class, true);
        AREA_TAG.put(FunctionTag.class, true);

    }

}
