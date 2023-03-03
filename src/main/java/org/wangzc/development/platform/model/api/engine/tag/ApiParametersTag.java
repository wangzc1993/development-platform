package org.wangzc.development.platform.model.api.engine.tag;

import java.util.List;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class ApiParametersTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        List<Object> list = engine.execute(context, getChildTags());

        for (int i = 0; i < list.size(); i++) {
            String name = String.valueOf(list.get(i));
            context.setArg(name, context.getApiParameters().get(name));
        }
        return null;
    }

    @Override
    public void check() throws TagCheckException {
        if (getChildTags().size() != ApiUtils.getTagListByType(getChildTags(), ParameterTag.class).size()) {
            throw new TagCheckException(getId(), "只能包含参数代码");
        }
        for (AbstractTag childTags : getChildTags()) {
            ParameterTag tag = (ParameterTag) childTags;
            if (!ParameterTag.TYPE_DEF.equals(tag.getType())) {
                throw new TagCheckException(getId(), "接口参数下的代码类型只能为定义参数类型");
            }
        }
    }

}
