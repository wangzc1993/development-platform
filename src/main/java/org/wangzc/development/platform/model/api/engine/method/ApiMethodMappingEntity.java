package org.wangzc.development.platform.model.api.engine.method;

import java.lang.reflect.Method;

import org.wangzc.development.platform.model.api.engine.config.ApiArgTypeEnum;

public class ApiMethodMappingEntity {
    private String name;
    private Class<?> clazz;
    private ApiMethodScopeEnum scope;
    private String description;
    private String methodName;
    private Method method;
    private Class<?>[] parametersType;
    private Class<?> returnType;
    private ApiArgTypeEnum[] targetParametersType;
    private ApiArgTypeEnum targetReturnType;

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?>[] getParametersType() {
        return parametersType;
    }

    public void setParametersType(Class<?>[] parametersType) {
        this.parametersType = parametersType;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public ApiArgTypeEnum[] getTargetParametersType() {
        return targetParametersType;
    }

    public void setTargetParametersType(ApiArgTypeEnum[] targetParametersType) {
        this.targetParametersType = targetParametersType;
    }

    public ApiArgTypeEnum getTargetReturnType() {
        return targetReturnType;
    }

    public void setTargetReturnType(ApiArgTypeEnum targetReturnType) {
        this.targetReturnType = targetReturnType;
    }

    public ApiMethodScopeEnum getScope() {
        return scope;
    }

    public void setScope(ApiMethodScopeEnum scope) {
        this.scope = scope;
    }

}