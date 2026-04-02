package client;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import specifications.RequestSpecBuilder;

import static io.restassured.RestAssured.given;

public class DogApiClient {
    private final AllureRestAssured allureFilter = new AllureRestAssured();

    public Response getAllBreeds() {
        return given()
                .filter(allureFilter)
                .spec(RequestSpecBuilder.buildDefaultRequestSpec())
                .log().all()
                .when()
                .get("/breeds/list/all");
    }

    public Response getImagesByBreed(String breed) {
        return given()
                .filter(allureFilter)
                .spec(RequestSpecBuilder.buildDefaultRequestSpec())
                .log().all()
                .pathParam("breed", breed)
                .when()
                .get("/breed/{breed}/images");
    }

    public Response getRandomImage() {
        return given()
                .filter(allureFilter)
                .spec(RequestSpecBuilder.buildDefaultRequestSpec())
                .log().all()
                .when()
                .get("/breeds/image/random");
    }
}