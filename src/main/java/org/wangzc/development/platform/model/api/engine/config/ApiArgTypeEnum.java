package org.wangzc.development.platform.model.api.engine.config;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public enum ApiArgTypeEnum {
    OBJECT(Object.class), STRING(String.class), NUMBER(BigDecimal.class), BOOLEAN(Boolean.class), LIST(List.class),
    MAP(Map.class);

    private Class<?> type;

    private ApiArgTypeEnum(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

}
