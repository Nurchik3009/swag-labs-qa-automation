package guru.qa.mobile.pages;

import com.codeborne.selenide.SelenideElement; import io.appium.java_client.AppiumBy; import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {


    private final SelenideElement backpackButton =
            $(AppiumBy.accessibilityId("test-ADD TO CART"));

    @Step("Добавляем товар в корзину")
    public ProductsPage addBackpackToCart() {
        backpackButton.click();
        return this;
    }
}