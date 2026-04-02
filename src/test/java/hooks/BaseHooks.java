package hooks;

import config.ConfigManager;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AllureJunit5.class)
public class BaseHooks {

    protected static ConfigManager configManager = ConfigManager.getInstance();

    @BeforeAll
    static void setupAll() {
        System.out.println("Starting setup");
        configManager.setup();
        RestAssured.baseURI = ConfigManager.getProperty("base.url");
    }
}