package org.wangzc.development.platform.model.api.engine.config;

import org.wangzc.development.platform.model.api.engine.tag.AbstractTag;
import org.wangzc.development.platform.model.api.engine.tag.AddTag;
import org.wangzc.development.platform.model.api.engine.tag.AndTag;
import org.wangzc.development.platform.model.api.engine.tag.ApiParametersTag;
import org.wangzc.development.platform.model.api.engine.tag.ArrayTag;
import org.wangzc.development.platform.model.api.engine.tag.BlankLineTag;
import org.wangzc.development.platform.model.api.engine.tag.BreakTag;
import org.wangzc.development.platform.model.api.engine.tag.CaseTag;
import org.wangzc.development.platform.model.api.engine.tag.ConditionTag;
import org.wangzc.development.platform.model.api.engine.tag.ContinueTag;
import org.wangzc.development.platform.model.api.engine.tag.DefaultTag;
import org.wangzc.development.platform.model.api.engine.tag.DescriptionTag;
import org.wangzc.development.platform.model.api.engine.tag.DivideTag;
import org.wangzc.development.platform.model.api.engine.tag.DoTag;
import org.wangzc.development.platform.model.api.engine.tag.ElseTag;
import org.wangzc.development.platform.model.api.engine.tag.EqualTag;
import org.wangzc.development.platform.model.api.engine.tag.ForTag;
import org.wangzc.development.platform.model.api.engine.tag.ForeachTag;
import org.wangzc.development.platform.model.api.engine.tag.FunctionTag;
import org.wangzc.development.platform.model.api.engine.tag.GreaterOrEqualTag;
import org.wangzc.development.platform.model.api.engine.tag.GreaterTag;
import org.wangzc.development.platform.model.api.engine.tag.IdTag;
import org.wangzc.development.platform.model.api.engine.tag.IfTag;
import org.wangzc.development.platform.model.api.engine.tag.ItemTag;
import org.wangzc.development.platform.model.api.engine.tag.LessOrEqualTag;
import org.wangzc.development.platform.model.api.engine.tag.LessTag;
import org.wangzc.development.platform.model.api.engine.tag.MapTag;
import org.wangzc.development.platform.model.api.engine.tag.MapValueTag;
import org.wangzc.development.platform.model.api.engine.tag.MetaTag;
import org.wangzc.development.platform.model.api.engine.tag.MultiplyTag;
import org.wangzc.development.platform.model.api.engine.tag.NotEqualTag;
import org.wangzc.development.platform.model.api.engine.tag.NotTag;
import org.wangzc.development.platform.model.api.engine.tag.NoteTag;
import org.wangzc.development.platform.model.api.engine.tag.OrTag;
import org.wangzc.development.platform.model.api.engine.tag.ParameterTag;
import org.wangzc.development.platform.model.api.engine.tag.ParametersTag;
import org.wangzc.development.platform.model.api.engine.tag.PrintTag;
import org.wangzc.development.platform.model.api.engine.tag.ReturnTag;
import org.wangzc.development.platform.model.api.engine.tag.SelectTag;
import org.wangzc.development.platform.model.api.engine.tag.StartTag;
import org.wangzc.development.platform.model.api.engine.tag.StepTag;
import org.wangzc.development.platform.model.api.engine.tag.SubtractTag;
import org.wangzc.development.platform.model.api.engine.tag.TargetTag;
import org.wangzc.development.platform.model.api.engine.tag.ValueTag;
import org.wangzc.development.platform.model.api.engine.tag.WhileTag;
import org.wangzc.development.platform.model.api.engine.tag.XmlTag;

public enum ApiTagEnum {
    XML("xml", "根", XmlTag.class),
    META("meta", "元数据", MetaTag.class),
    ID("id", "ID", IdTag.class),
    DESCRIPTION("description", "描述", DescriptionTag.class),
    API_PARAMETERS("api-parameters", "接口参数列表", ApiParametersTag.class),
    NOTE("note", "注释", NoteTag.class),
    BLANK_LINE("blank-line", "空行", BlankLineTag.class),
    PRINT("print", "打印日志", PrintTag.class),
    VALUE("value", "变量", ValueTag.class),
    MAP("map", "对象", MapTag.class),
    MAP_VALUE("map-value", "对象键值", MapValueTag.class),
    ARRAY("array", "数组", ArrayTag.class),
    IF("if", "判断", IfTag.class),
    CONDITION("condition", "判断条件", ConditionTag.class),
    DO("do", "执行代码", DoTag.class),
    ELSE("else", "否则", ElseTag.class),
    WHILE("while", "条件循环", WhileTag.class),
    FOREACH("foreach", "对象循环", ForeachTag.class),
    ITEM("item", "循环对象", ItemTag.class),
    FOR("for", "递进循环", ForTag.class),
    BREAK("break", "终止循环", BreakTag.class),
    CONTINUE("continue", "跳过循环", ContinueTag.class),
    START("start", "开始变量", StartTag.class),
    STEP("step", "递进值", StepTag.class),
    SELECT("select", "选择", SelectTag.class),
    TARGET("target", "选择目标", TargetTag.class),
    CASE("case", "目标选项", CaseTag.class),
    DEFAULT("default", "默认选项", DefaultTag.class),
    FUNCTION("function", "方法", FunctionTag.class),
    PARAMETERS("parameters", "参数列表", ParametersTag.class),
    PARAMETER("parameter", "参数", ParameterTag.class),
    RETURN("return", "返回", ReturnTag.class),
    ADD("add", "加", AddTag.class),
    SUBTRACT("subtract", "减", SubtractTag.class),
    DIVIDE("divide", "除", DivideTag.class),
    MULTIPLY("multiply", "乘", MultiplyTag.class),
    GREATER_OR_EQUAL("greater-or-equal", "大于等于", GreaterOrEqualTag.class),
    LESS_OR_EQUAL("less-or-equal", "小于等于", LessOrEqualTag.class),
    EQUAL("equal", "等于", EqualTag.class),
    NOT_EQUAL("not-equal", "不等", NotEqualTag.class),
    NOT("not", "非", NotTag.class),
    GREATER("greater", "大于", GreaterTag.class),
    LESS("less", "小于", LessTag.class),
    AND("and", "与", AndTag.class),
    OR("or", "或", OrTag.class);

    private final String tag;

    private final String name;

    private final Class<? extends AbstractTag> type;

    private ApiTagEnum(String tag, String name, Class<? extends AbstractTag> type) {
        this.tag = tag;
        this.name = name;
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public Class<? extends AbstractTag> getType() {
        return type;
    }
}
