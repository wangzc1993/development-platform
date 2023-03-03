package org.wangzc.development.platform.model.api.engine.tag;

import java.util.List;

import org.nutz.lang.Strings;
import org.wangzc.development.platform.model.api.engine.ApiContext;
import org.wangzc.development.platform.model.api.engine.ApiEngine;
import org.wangzc.development.platform.model.api.engine.ApiUtils;
import org.wangzc.development.platform.model.api.engine.exception.MethodException;
import org.wangzc.development.platform.model.api.engine.exception.ReturnException;
import org.wangzc.development.platform.model.api.engine.exception.TagCheckException;
import org.wangzc.development.platform.model.api.engine.exception.TagException;
import org.wangzc.development.platform.model.api.engine.method.ApiMethodMapping;

public class FunctionTag extends AbstractTag {

    private static final String TYPE_DEF = "def";

    private static final String TYPE_REF = "ref";

    private String type;

    private String name;

    private String ref;

    @Override
    public Object execute(ApiContext context, ApiEngine engine) throws TagException {
        if (Strings.isNotBlank(name)) {
            context.setFunction(name, this);
            return null;
        }
        FunctionTag defFunction = context.getFunction(ref);
        if (null != defFunction) {
            // 获取方法定义的参数名称
            ParametersTag defParametersTag = ApiUtils.getTagByType(defFunction.getChildTags(), ParametersTag.class);
            Object defParamNames = engine.execute(context, defParametersTag);
            if (!List.class.isAssignableFrom(defParamNames.getClass())) {
                throw new TagException("方法参数定义类型错误");
            }
            List<?> defParams = (List<?>) defParamNames;
            // 获取执行方法的参数
            ParametersTag parameters = ApiUtils.getTagByType(getChildTags(), ParametersTag.class);
            Object execute = engine.execute(context, parameters);
            if (!List.class.isAssignableFrom(execute.getClass())) {
                throw new TagException("方法参数类型错误");
            }
            List<?> list = (List<?>) execute;
            if (defParams.size() != list.size()) {
                throw new TagException("方法参数数量错误");
            }
            for (int i = 0; i < list.size(); i++) {
                context.setArg(String.valueOf(defParams.get(i)), list.get(i));
            }
            DoTag doTag = ApiUtils.getTagByType(defFunction.getChildTags(), DoTag.class);
            try {
                engine.execute(context, doTag);
                return null;
            } catch (ReturnException e) {
                return e.getData();
            }
        }

        ParametersTag parameters = ApiUtils.getTagByType(getChildTags(), ParametersTag.class);
        Object execute = engine.execute(context, parameters);
        if (!List.class.isAssignableFrom(execute.getClass())) {
            throw new TagException("方法参数类型错误");
        }
        List<?> list = (List<?>) execute;
        try {
            return ApiMethodMapping.invoke(ref, list.toArray(new Object[0]));
        } catch (MethodException e) {
            throw new TagException(e.getMessage(), e);
        }
    }

    @Override
    public void check() throws TagCheckException {
        if (Strings.isBlank(type)) {
            throw new TagCheckException(getId(), "缺少属性[类型]");
        }
        if (!TYPE_REF.equals(type) && !TYPE_DEF.equals(type)) {
            throw new TagCheckException(getId(), "属性[类型]不正确");
        }
        if (null == ApiUtils.getTagByType(getChildTags(), ParametersTag.class)) {
            throw new TagCheckException(getId(), "缺少变量列表");
        }
        if (TYPE_DEF.equals(type)) {
            if (Strings.isBlank(name)) {
                throw new TagCheckException(getId(), "缺少方法名称");
            }
            if (null == ApiUtils.getTagByType(getChildTags(), DoTag.class)) {
                throw new TagCheckException(getId(), "缺少执行代码");
            }
        }
        if (TYPE_REF.equals(type)) {

        }
    }

}
