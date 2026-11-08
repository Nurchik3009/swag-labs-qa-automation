package guru.qa.api.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static guru.qa.api.specs.BaseSpec.baseRequestSpec;

public class AuthApiClient {

    @Step("Авторизация и получение access-токена")
    public String loginAndGetAccessToken(Map<String, String> credentials) {
        Response response = given(baseRequestSpec)
                .body(credentials)
                .when()
                .post("/auth/token/")
                .then()
                .statusCode(200)
                .extract().response();

        return response.path("access");
    }
}