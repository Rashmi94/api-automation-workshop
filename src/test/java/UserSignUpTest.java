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


        Response response = RestAssured.given().baseUri(RestAssured.baseURI)
                .contentType(ContentType.JSON)
                .body(signupRequestBody).when().post(signupEndpointResource);
        int statusCode = response.getStatusCode();
        String authenticatedEmail = response.jsonPath().get("data.user.email");
        String role = response.jsonPath().get("data.user.role");
        String accessToken = response.jsonPath().get("data.session.access_token");

        Assert.assertEquals(statusCode,201,"Invalid username or password");
        Assert.assertEquals(authenticatedEmail,email,"incorrect email");
        Assert.assertEquals(role,"authenticated","failed to validate the role to be authenticated");
        Assert.assertNotNull(accessToken);

    }
}
