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
@Tag("breeds")
@Tag("regression")
public class BreedsListTest extends BaseHooks {

    private final DogApiClient dogApiClient = new DogApiClient();

    @Test
    @DisplayName("Should return all breeds successfully")
    void shouldReturnAllBreedsSuccessfully() {
        Response response = dogApiClient.getAllBreeds();

        response.then()
                .spec(ResponseSpecBuilder.buildSuccessResponseSpec())
                .body("status", equalTo("success"))
                .body("message", notNullValue())
                .log().all();
    }

    @Test
    @DisplayName("Should return known breeds with sub breeds as arrays")
    void shouldReturnSubBreedsAsArrays() {
        Response response = dogApiClient.getAllBreeds();

        response.then()
                .spec(ResponseSpecBuilder.buildSuccessResponseSpec())
                .body("status", equalTo("success"))
                .body("message.hound", instanceOf(java.util.List.class))
                .body("message.bulldog", instanceOf(java.util.List.class))
                .body("message.pug", instanceOf(java.util.List.class))
                .log().all();
    }
}
