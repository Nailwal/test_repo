package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            // Load environment properties file based on the `env` system property
            String env = System.getProperty("env", "staging");
            FileInputStream fis = new FileInputStream("src/main/resources/" + env + ".properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file for environment.", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}