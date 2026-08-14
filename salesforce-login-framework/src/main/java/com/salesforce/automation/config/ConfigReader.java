package com.salesforce.automation.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties = new Properties();

    public ConfigReader(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found on classpath: " + fileName);
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file: " + fileName, e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Missing required configuration property: " + key);
        }
        return value.trim();
    }
}
