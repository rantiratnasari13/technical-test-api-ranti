import controller.PunkApiV2Controller;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class PunkApiV2ControllerTest {



    public static void main(String args[]) {
        PunkApiV2Controller punkApiV2Controller = new PunkApiV2Controller();

        final String baseUrl= "https://api.punkapi.com/v2/beers";
        Response response = given().when().get(baseUrl);

        //API Test for scenario 4 - Verify the amount of data returned is valid
        int[] numberOfData = {1, 5, 20};
        System.out.println("Scenario 4");
        for (int i=0; i<numberOfData.length; i++){
            punkApiV2Controller.getDataBasedOnNumberOfPage(numberOfData[i], baseUrl);
        }

        //API Test for scenario 5 - Verify JSON schema
        System.out.println("Scenario 5");
        punkApiV2Controller.schemaValidation(baseUrl);

        //API Test for scenario 5a - Verify that the amount of data returned
        System.out.println("Scenario 5a");
        punkApiV2Controller.getDataAmount(response);

        //API Test for scenario 5b - Print all returned “name” of list that endpoint data
        System.out.println("Scenario 5b");
        punkApiV2Controller.getDataName(response);
    }


}
