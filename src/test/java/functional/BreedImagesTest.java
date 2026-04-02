package functional;

import client.DogApiClient;
import hooks.BaseHooks;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specifications.ResponseSpecBuilder;

import static org.hamcrest.Matchers.*;

@Tag("functional")
@Tag("breed-images")
@Tag("regression")
public class BreedImagesTest extends BaseHooks {

    private final DogApiClient dogApiClient = new DogApiClient();

    @Test
    @DisplayName("Should return images and image urls for a valid breed")
    void shouldReturnImagesForAValidBreed() {
        Response response = dogApiClient.getImagesByBreed("hound");

        response.then()
                .spec(ResponseSpecBuilder.buildSuccessResponseSpec())
                .body("status", equalTo("success"))
                .body("message", notNullValue())
                .body("message", not(empty()))
                .body("message", instanceOf(java.util.List.class))
                .body("message[0]", anyOf(startsWith("https://"), startsWith("http://")))
                .log().all();
    }

    @Test
    @DisplayName("Should return failure for invalid breed")
    void shouldReturnFailureForInvalidBreed() {
        Response response = dogApiClient.getImagesByBreed("invalidbreedxyz");

        response.then()
                .spec(ResponseSpecBuilder.buildNotFoundResponseSpec())
                .body("status", equalTo("error"))
                .body("message", equalTo("Breed not found (main breed does not exist)"))
                .log().all();
    }
}