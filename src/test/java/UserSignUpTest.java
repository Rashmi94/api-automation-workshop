import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserSignUpTest extends BaseAPITest {

    @Test
    public void successfulRegistration() {
        String signupEndpointResource = "/api/auth/signup";
        String email = "testrashmi1@mail.com";
        String password = "testvagrant";

        String signupRequestBody = String.format("{\"email\":\"%s\",\"password\" : \"%s\"}",email, password);


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestBody).when().post(signupEndpointResource);
        int statusCode = response.getStatusCode();
        String authenticatedEmail = response.jsonPath().get("data.user.email");
        String role = response.jsonPath().get("data.user.role");
        String accessToken = response.jsonPath().get("data.session.access_token");

        Assert.assertEquals(statusCode,201,"Expected HTTP status code 201 for successful registration but got " + statusCode);
        Assert.assertEquals(authenticatedEmail,email,"Expected the registered email to match the email used for signup but found " + authenticatedEmail);
        Assert.assertEquals(role,"authenticated","Expected the role of the user to be 'authenticated' after signup but found " + role);
        Assert.assertNotNull(accessToken,"Expected a non-null access token after successful registration but found it to be null");

    }
}
