package org.wangzc.development.platform;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevelopmentPlatformConfig {

    @Value("${platform.file.path}")
    private String filePath;

    @Value("${platform.file.api.path}")
    private String apiPath;

    @Value("${platform.file.view.path}")
    private String viewPath;

    @Value("${platform.file.database.path}")
    private String databasePath;

    @Value("${platform.file.table.path}")
    private String tablePath;

    @Value("${platform.file.resource.path}")
    private String resourcePath;

    @Value("${platform.file.method-mapping.path}")
    private String methodMappingPath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public String getDatabasePath() {
        return databasePath;
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    public String getTablePath() {
        return tablePath;
    }

    public void setTablePath(String tablePath) {
        this.tablePath = tablePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getMethodMappingPath() {
        return methodMappingPath;
    }

    public void setMethodMappingPath(String methodMapping) {
        this.methodMappingPath = methodMapping;
    }

}
