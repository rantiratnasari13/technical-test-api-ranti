package controller;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class PunkApiV2Controller {



    public void getDataBasedOnNumberOfPage(int numberOfData, String baseUrl){
        given().queryParam("page","2")
                .queryParam("per_page", numberOfData)
                .when().get(baseUrl).then().assertThat()
                .statusCode(200)
                .body("size()", is(numberOfData));
    }

    public void schemaValidation(String baseUrl){
        given().when().get(baseUrl)
                .then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/Schema/schema.json")));
    }

    public void getDataAmount(Response response){
        JsonPath json = new JsonPath(response.asString());
        int size = json.getInt("Location.size()");
        System.out.println("Amount: " +size);
    }

    public void getDataName(Response response){
        String name = response.jsonPath().getString("name");
        System.out.println(name);
    }


}
