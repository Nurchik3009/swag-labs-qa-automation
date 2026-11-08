
package guru.qa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    private final SelenideElement firstNameInput = $("#first-name");
    private final SelenideElement lastNameInput = $("#last-name");
    private final SelenideElement postalCodeInput = $("#postal-code");

    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement finishButton = $("#finish");

    private final SelenideElement errorMessage = $("[data-test='error']");
    private final SelenideElement successHeader = $(".complete-header");

    @Step("Заполнить данные покупателя")
    public CheckoutPage fillCustomerInfo(
            String firstName,
            String lastName,
            String postalCode
    ) {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        postalCodeInput.setValue(postalCode);
        return this;
    }

    @Step("Нажать кнопку Continue")
    public CheckoutPage clickContinue() {
        continueButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить сообщение об ошибке: {expectedError}")
    public CheckoutPage checkErrorMessage(String expectedError) {
        errorMessage.shouldBe(visible)
                .shouldHave(exactText(expectedError));
        return this;
    }

    @Step("Нажать кнопку Finish")
    public CheckoutPage clickFinish() {
        finishButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить успешное оформление заказа: {expectedMessage}")
    public CheckoutPage checkSuccessMessage(String expectedMessage) {
        successHeader.shouldBe(visible)
                .shouldHave(exactText(expectedMessage));
        return this;
    }
}
