package com.swt.jxproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class SwaggerProperties {
    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    @Value("${swagger.enable:false}")
    private Boolean enable;

    /**
     * 项目应用名
     */
    @Value("${swagger.application-name:jxproject}")
    private String applicationName;

    /**
     * 项目版本信息
     */
    @Value("${swagger.application-version:1.0}")
    private String applicationVersion;

    /**
     * 项目描述信息
     */
    @Value("${swagger.application-description:1}")
    private String applicationDescription;

    /**
     * 接口调试地址
     */

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

}

