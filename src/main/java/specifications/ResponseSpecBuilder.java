package specifications;

import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

public class ResponseSpecBuilder {

    public static ResponseSpecification buildSuccessResponseSpec() {
        return new io.restassured.builder.ResponseSpecBuilder()
                .expectStatusCode(SC_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification buildNotFoundResponseSpec() {
        return new io.restassured.builder.ResponseSpecBuilder()
                .expectStatusCode(SC_NOT_FOUND)
                .expectContentType(ContentType.JSON)
                .build();
    }
}