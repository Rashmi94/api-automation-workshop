package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.UUID;

public class UserClient {

    public Response createUser(String email, String password){
        String signupEndpointResource = "/api/auth/signup";
        String signupRequestBody = String.format("{\"email\":\"%s\",\"password\" : \"%s\"}",email, password);


        Response response = RestAssured.given().baseUri(RestAssured.baseURI)
                .contentType(ContentType.JSON)
                .body(signupRequestBody).when().post(signupEndpointResource);
        return response;
    }

}
