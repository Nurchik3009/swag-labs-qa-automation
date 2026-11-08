
package guru.qa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $("[data-test='error']");

    @Step("Открыть страницу авторизации SauceDemo")
    public LoginPage openPage() {
        open("/");
        return this;
    }

    @Step("Ввести логин пользователя")
    public LoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    @Step("Ввести пароль пользователя")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку Login")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Авторизоваться пользователем: {username}")
    public LoginPage login(String username, String password) {
        openPage();
        setUsername(username);
        setPassword(password);
        clickLoginButton();
        return this;
    }

    @Step("Проверить сообщение об ошибке: {expectedError}")
    public LoginPage checkErrorMessage(String expectedError) {
        errorMessage.shouldBe(visible)
                .shouldHave(text(expectedError));
        return this;
    }

    @Step("Проверить отображение страницы авторизации")
    public LoginPage checkLoginPageDisplayed() {
        loginButton.shouldBe(visible);
        usernameInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        return this;
    }
}
