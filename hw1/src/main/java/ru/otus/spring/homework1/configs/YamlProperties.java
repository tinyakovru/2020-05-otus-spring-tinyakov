package ru.otus.spring.homework1.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "settings")
public class YamlProperties {
    private int thresholdValue;
    private String resourcePath;
    private Locale locale;

    public void setThresholdValue(int thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getThresholdValue() {
        return thresholdValue;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public Locale getLocale() {
        return locale;
    }
}
