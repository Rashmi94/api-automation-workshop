import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utilities.PropertyFileUtils;

public class BaseAPITest {

    @BeforeClass
    public void setup() {
        // Base URL fetch from property file
        String BASE_URL = PropertyFileUtils.getProperty("base.url");

        // Set RestAssured configurations
        RestAssured.baseURI = BASE_URL;
    }
}