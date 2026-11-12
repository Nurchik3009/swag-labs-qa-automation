package guru.qa.mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ProductsScreen {

    private final SelenideElement backpackButton =
            $(AppiumBy.accessibilityId("test-ADD TO CART"));

    @Step("Добавить рюкзак в корзину")
    public ProductsScreen addBackpackToCart() {
        backpackButton.click();
        return this;
    }
}