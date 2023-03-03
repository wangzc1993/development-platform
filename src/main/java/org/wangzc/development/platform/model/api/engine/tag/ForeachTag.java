package org.wangzc.development.platform.model.api.engine.tag;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nutz.lang.Strings;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.BreakException;
import org.wangzc.development.platform.model.api.engine.exception.ContinueException;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class ForeachTag extends AbstractTag {

    private String key;

    private String value;

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        ItemTag itemTag = ApiUtils.getTagByType(getChildTags(), ItemTag.class);
        DoTag doTag = ApiUtils.getTagByType(getChildTags(), DoTag.class);
        Object item = engine.execute(context, itemTag);
        if (List.class.isAssignableFrom(item.getClass())) {
            List<?> list = (List<?>) item;
            Map<Integer, Object> map = new LinkedHashMap<>(list.size());
            for (int i = 0; i < list.size(); i++) {
                map.put(i, list.get(i));
            }
            each(map, context, engine, doTag);
            return null;
        }
        if (Map.class.isAssignableFrom(item.getClass())) {
            Map<?, ?> map = (Map<?, ?>) item;
            each(map, context, engine, doTag);
            return null;
        }

        return null;
    }

    private void each(Map<?, ?> map, ApiContext context, ApiEngine engine, DoTag doTag)
            throws TagException {
        for (Entry<?, ?> entrySet : map.entrySet()) {
            context.setArg(key, entrySet.getKey());
            context.setArg(value, entrySet.getValue());
            try {
                engine.execute(context, doTag);
            } catch (BreakException e) {
                break;
            } catch (ContinueException e) {
                continue;
            }
        }

    }

    @Override
    public void check() throws TagCheckException {
        if (Strings.isBlank(key)) {
            throw new TagCheckException(getId(), "属性[键名称]不可为空");
        }
        if (Strings.isBlank(value)) {
            throw new TagCheckException(getId(), "属性[值名称]不可为空");
        }
        if (null == ApiUtils.getTagByType(getChildTags(), ItemTag.class)) {
            throw new TagCheckException(getId(), "缺少循环对象代码");
        }
        if (null == ApiUtils.getTagByType(getChildTags(), DoTag.class)) {
            throw new TagCheckException(getId(), "缺少执行代码");
        }
    }
}
