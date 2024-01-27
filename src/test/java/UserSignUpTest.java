import clients.UserClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class UserSignUpTest extends BaseAPITest {

    @Test
    public void successfulRegistration() {
        String signupEndpointResource = "/api/auth/signup";
        String email = UUID.randomUUID()+"@mail.com";
        String password = "1234567890";

        UserClient userClient = new UserClient();
        Response response = userClient.createUser(email,password);

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
