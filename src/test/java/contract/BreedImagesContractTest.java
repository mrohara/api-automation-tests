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
@Tag("breed-images-contract")
@Tag("regression")
public class BreedImagesContractTest extends BaseHooks {

    private final DogApiClient dogApiClient = new DogApiClient();

    @Test
    @DisplayName("Should validate breed images schema")
    void shouldValidateBreedImagesSchema() {
        Response response = dogApiClient.getImagesByBreed("hound");
        response.then()
                .spec(ResponseSpecBuilder.buildSuccessResponseSpec())
                .body(matchesJsonSchemaInClasspath("schemas/breed-images-schema.json"))
                .log()
                .all();
    }
}
