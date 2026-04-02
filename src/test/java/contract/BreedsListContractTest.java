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
@Tag("breeds-contract")
@Tag("regression")
public class BreedsListContractTest extends BaseHooks {

    private final DogApiClient dogApiClient = new DogApiClient();

    @Test
    @DisplayName("Should validate breeds list schema")
    void shouldValidateBreedsListSchema() {
        Response response = dogApiClient.getAllBreeds();
        response.then()
                .spec(ResponseSpecBuilder.buildSuccessResponseSpec())
                .body(matchesJsonSchemaInClasspath("schemas/breeds-list-all-schema.json"))
                .log()
                .all();
    }
}