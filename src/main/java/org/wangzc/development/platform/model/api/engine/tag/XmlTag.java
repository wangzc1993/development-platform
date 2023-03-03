package org.wangzc.development.platform.model.api.engine.tag;

import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;

public class XmlTag extends AbstractTag {

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        System.out.println("--------开始执行脚本 " + getId());
        engine.execute(context, getChildTags());
        System.out.println("--------脚本执行完成 " + getId());
        return null;
    }

    @Override
    public void check() throws TagCheckException {

    }

}
