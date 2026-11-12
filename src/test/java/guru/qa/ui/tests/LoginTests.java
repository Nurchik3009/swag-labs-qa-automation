package guru.qa.ui.tests;

import guru.qa.ui.BaseTest;
import guru.qa.ui.TestData;
import guru.qa.ui.pages.LoginPage;
import guru.qa.ui.pages.ProductsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("UI Automation")
@Feature("Authentication")
@Owner("Ainur Kaliakbarova")
@Tag("ui")
public class LoginTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();

    @Test
    @Story("Successful login")
    @DisplayName("Успешная авторизация стандартного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    void successfulLoginTest() {
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        productsPage.checkTitle(TestData.PRODUCTS_PAGE_TITLE);
    }

    @Test
    @Story("Negative login scenarios")
    @DisplayName("Ошибка авторизации заблокированного пользователя")
    @Severity(SeverityLevel.CRITICAL)
    void lockedOutUserLoginTest() {
        loginPage.login(TestData.LOCKED_OUT_USER, TestData.PASSWORD)
                .checkErrorMessage(TestData.LOCKED_OUT_ERROR);
    }

    @Test
    @Story("Negative login scenarios")
    @DisplayName("Ошибка авторизации при вводе неверного пароля")
    @Severity(SeverityLevel.NORMAL)
    void invalidPasswordTest() {
        loginPage.login(TestData.STANDARD_USER, TestData.INVALID_PASSWORD)
                .checkErrorMessage(TestData.INVALID_CREDENTIALS_ERROR);
    }

    @Test
    @Story("Logout")
    @DisplayName("Успешный выход пользователя из системы")
    @Severity(SeverityLevel.NORMAL)
    void logoutTest() {
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        productsPage.logout()
                .checkLoginPageDisplayed();
    }
}