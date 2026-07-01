package com.automation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration loader for reading properties from config.properties file
 */
public class Config {

    private static Properties properties;

    static {
        loadProperties();
    }

    /**
     * Load properties from config.properties file
     */
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.out.println("config.properties file not found. Using default constants.");
            }
        } catch (IOException e) {
            System.out.println("Error loading config.properties: " + e.getMessage());
        }
    }

    /**
     * Get property value by key
     *
     * @param key The property key
     * @return The property value or empty string if not found
     */
    public static String getProperty(String key) {
        return properties.getProperty(key, "");
    }

    /**
     * Get property value by key with default value
     *
     * @param key          The property key
     * @param defaultValue Default value if key not found
     * @return The property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get application URL
     *
     * @return Application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url", "https://your-app-domain.com");
    }

    /**
     * Get OTP API endpoint
     *
     * @return OTP API endpoint URL
     */
    public static String getOtpApiEndpoint() {
        return getProperty("otp.api.endpoint", "https://your-app-domain.com/api/send-otp");
    }

    /**
     * Get wait timeout in seconds
     *
     * @return Wait timeout
     */
    public static int getWaitTimeout() {
        String timeout = getProperty("wait.timeout.seconds", "10");
        try {
            return Integer.parseInt(timeout);
        } catch (NumberFormatException e) {
            return 10;
        }
    }

}
