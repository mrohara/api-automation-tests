package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {


    private static ConfigManager instance;
    private String environment;
    private static Properties properties = new Properties();

    static {

        try {

            String env = System.getProperty("env");

            String fileName = "config/" + env + "-data.properties";

            InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName);

            if (input == null) {
                throw new RuntimeException("Config file not found: " + fileName);
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Error loading properties file", e);
        }

    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static ConfigManager getInstance() {

        if (instance == null) {
            instance = new ConfigManager();
        }

        return instance;
    }

    private void initEnvVariables() {
        this.environment = System.getenv("ENV");
    }

    public void setup() {
        this.initEnvVariables();
    }



    public String getEnvironment(){
        return this.environment;
    }

}

