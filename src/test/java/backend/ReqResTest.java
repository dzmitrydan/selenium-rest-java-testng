package backend;

import backend.model.UserRequest;
import backend.model.UserResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqResTest {

    private final RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setBasePath("/api/users")
            .setContentType(ContentType.JSON)
            .build().filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    @Test
    public void getUser() {
        given()
                .spec(reqSpec)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("data[3].email", equalTo("eve.holt@reqres.in"));
    }

    @Test
    public void createUser() {
        UserRequest userRequest = new UserRequest("Dog", "animal");

        UserResponse userResponse = given()
                .spec(reqSpec)
                .body(userRequest)
                .when()
                .post()
                .then()
                .extract()
                .as(UserResponse.class);
        Assert.assertEquals(userResponse.getName(), userRequest.getName());
    }

    @Test
    public void validateFirstNameNotEmpty() {
        List<String> firstNames = given()
                .spec(reqSpec)
                .get()
                .then()
                .extract()
                .path("data.first_name");
        System.out.println(firstNames);
        Assert.assertFalse(firstNames.isEmpty());
    }
}
