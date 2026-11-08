package guru.qa.mobile.tests;

import guru.qa.mobile.BaseTest;
import guru.qa.mobile.pages.LoginPage;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("mobile")
public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void successfulLoginTest() {

        loginPage.login("standard_user", "secret_sauce");

        checkProductsPageIsVisible();
    }

    @Step("Проверяем, что открылась страница PRODUCTS")
    void checkProductsPageIsVisible() {
        $(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"PRODUCTS\")"
        )).shouldBe(visible);
    }

    @Test
    void loginWithInvalidCredentialsTest() {

        loginPage.login("standard_user", "wrong_password");

        String errorMessage = loginPage.getErrorMessage();

        checkAuthorizationError(errorMessage);
    }

    @Step("Проверяем сообщение об ошибке авторизации")
    void checkAuthorizationError(String errorMessage) {
        assertTrue(
                errorMessage.contains("Username and password do not match"),
                "Expected authorization error message, but got: " + errorMessage
        );
    }
}