package specifications;

import config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilder {

    public static RequestSpecification buildDefaultRequestSpec() {
        return new io.restassured.builder.RequestSpecBuilder()
                .setBaseUri(ConfigManager.getProperty("base.url"))
                .setContentType(ContentType.JSON)
                .build();
    }
}