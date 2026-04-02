package contract;

import client.DogApiClient;
import hooks.BaseHooks;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specifications.ResponseSpecBuilder;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Tag("contract")
@Tag("random-image-contract")
@Tag("regression")
public class RandomImageContractTest extends BaseHooks {

    private final DogApiClient dogApiClient = new DogApiClient();

    @Test
    @DisplayName("Should validate random image schema")
    void shouldValidateRandomImageSchema() {
        Response response = dogApiClient.getRandomImage();
        response.then()
                .spec(ResponseSpecBuilder.buildSuccessResponseSpec())
                .body(matchesJsonSchemaInClasspath("schemas/random-image-schema.json"))
                .log()
                .all();

    }
}