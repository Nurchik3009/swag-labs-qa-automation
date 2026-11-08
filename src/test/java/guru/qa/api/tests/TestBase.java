
package guru.qa.api.tests;

import guru.qa.api.api.ApiClient;
import guru.qa.api.config.ApiConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    protected static final ApiClient api = new ApiClient();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = ApiConfig.BASE_URL;
    }
}
