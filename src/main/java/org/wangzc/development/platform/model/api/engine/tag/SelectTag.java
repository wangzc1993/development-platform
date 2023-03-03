package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class SelectTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        TargetTag targetTag = ApiUtils.getTagByType(getChildTags(), TargetTag.class);
        DefaultTag defaultTag = ApiUtils.getTagByType(getChildTags(), DefaultTag.class);
        Object target = engine.execute(context, targetTag);
        for (AbstractTag child : getChildTags()) {
            if (CaseTag.class.isAssignableFrom(child.getClass())) {
                CaseTag caseTag = (CaseTag) child;
                TargetTag caseTargetTag = ApiUtils.getTagByType(caseTag.getChildTags(), TargetTag.class);
                DoTag caseDoTag = ApiUtils.getTagByType(caseTag.getChildTags(), DoTag.class);
                if (ApiUtils.isEqual(target, engine.execute(context, caseTargetTag))) {
                    engine.execute(context, caseDoTag);
                    return null;
                }
            }
        }
        if (null != defaultTag) {
            engine.execute(context, defaultTag);
        }
        return null;
    }

    @Override
    public void check() throws TagCheckException {
        if (null == ApiUtils.getTagByType(getChildTags(), TargetTag.class)) {
            throw new TagCheckException(getId(), "缺少选择目标代码");
        }
        if (0 == ApiUtils.getTagListByType(getChildTags(), CaseTag.class).size()) {
            throw new TagCheckException(getId(), "至少包含一个目标选项代码");
        }
    }

}
