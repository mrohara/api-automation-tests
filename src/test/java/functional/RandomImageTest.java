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
@Tag("random-image")
@Tag("regression")
public class RandomImageTest extends BaseHooks {

    private final DogApiClient dogApiClient = new DogApiClient();

    @Test
    @DisplayName("Should return a random dog image successfully")
    void shouldReturnARandomDogImageSuccessfully() {
        Response response = dogApiClient.getRandomImage();

        response.then()
                .spec(ResponseSpecBuilder.buildSuccessResponseSpec())
                .body("status", equalTo("success"))
                .body("message", notNullValue())
                .body("message", not(blankOrNullString()))
                .log().all();
    }

    @Test
    @DisplayName("Should return a valid random image url")
    void shouldReturnAValidRandomImageUrl() {
        Response response = dogApiClient.getRandomImage();

        response.then()
                .spec(ResponseSpecBuilder.buildSuccessResponseSpec())
                .body("status", equalTo("success"))
                .body("message", anyOf(startsWith("https://"), startsWith("http://")))
                .log().all();
    }
}
