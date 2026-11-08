
package guru.qa.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement usernameInput =
            $(AppiumBy.accessibilityId("test-Username"));

    private final SelenideElement passwordInput =
            $(AppiumBy.accessibilityId("test-Password"));

    private final SelenideElement loginButton =
            $(AppiumBy.accessibilityId("test-LOGIN"));

    private final SelenideElement errorMessage =
            $(AppiumBy.accessibilityId("test-Error message"));

    @Step("Вводим логин: {username}")
    public LoginPage enterUsername(String username) {
        usernameInput.click();
        usernameInput.sendKeys(username);
        return this;
    }

    @Step("Вводим пароль")
    public LoginPage enterPassword(String password) {
        passwordInput.click();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажимаем кнопку Login")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Получаем сообщение об ошибке авторизации")
    public String getErrorMessage() {
        return errorMessage.$("android.widget.TextView").getText();
    }

    @Step("Выполняем авторизацию пользователя")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
