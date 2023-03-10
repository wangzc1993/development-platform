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
    XML("xml", "???", XmlTag.class),
    META("meta", "?????????", MetaTag.class),
    ID("id", "ID", IdTag.class),
    DESCRIPTION("description", "??????", DescriptionTag.class),
    API_PARAMETERS("api-parameters", "??????????????????", ApiParametersTag.class),
    NOTE("note", "??????", NoteTag.class),
    BLANK_LINE("blank-line", "??????", BlankLineTag.class),
    PRINT("print", "????????????", PrintTag.class),
    VALUE("value", "??????", ValueTag.class),
    MAP("map", "??????", MapTag.class),
    MAP_VALUE("map-value", "????????????", MapValueTag.class),
    ARRAY("array", "??????", ArrayTag.class),
    IF("if", "??????", IfTag.class),
    CONDITION("condition", "????????????", ConditionTag.class),
    DO("do", "????????????", DoTag.class),
    ELSE("else", "??????", ElseTag.class),
    WHILE("while", "????????????", WhileTag.class),
    FOREACH("foreach", "????????????", ForeachTag.class),
    ITEM("item", "????????????", ItemTag.class),
    FOR("for", "????????????", ForTag.class),
    BREAK("break", "????????????", BreakTag.class),
    CONTINUE("continue", "????????????", ContinueTag.class),
    START("start", "????????????", StartTag.class),
    STEP("step", "?????????", StepTag.class),
    SELECT("select", "??????", SelectTag.class),
    TARGET("target", "????????????", TargetTag.class),
    CASE("case", "????????????", CaseTag.class),
    DEFAULT("default", "????????????", DefaultTag.class),
    FUNCTION("function", "??????", FunctionTag.class),
    PARAMETERS("parameters", "????????????", ParametersTag.class),
    PARAMETER("parameter", "??????", ParameterTag.class),
    RETURN("return", "??????", ReturnTag.class),
    ADD("add", "???", AddTag.class),
    SUBTRACT("subtract", "???", SubtractTag.class),
    DIVIDE("divide", "???", DivideTag.class),
    MULTIPLY("multiply", "???", MultiplyTag.class),
    GREATER_OR_EQUAL("greater-or-equal", "????????????", GreaterOrEqualTag.class),
    LESS_OR_EQUAL("less-or-equal", "????????????", LessOrEqualTag.class),
    EQUAL("equal", "??????", EqualTag.class),
    NOT_EQUAL("not-equal", "??????", NotEqualTag.class),
    NOT("not", "???", NotTag.class),
    GREATER("greater", "??????", GreaterTag.class),
    LESS("less", "??????", LessTag.class),
    AND("and", "???", AndTag.class),
    OR("or", "???", OrTag.class);

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
