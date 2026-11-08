
package guru.qa.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private final ElementsCollection cartItems = $$(".cart_item");
    private final SelenideElement cartItemName = $(".inventory_item_name");
    private final SelenideElement removeBackpackButton =
            $("#remove-sauce-labs-backpack");
    private final SelenideElement checkoutButton = $("#checkout");

    @Step("Проверить наличие товара в корзине: {expectedItemName}")
    public CartPage checkItemInCart(String expectedItemName) {
        cartItemName.shouldBe(visible)
                .shouldHave(exactText(expectedItemName));
        return this;
    }

    @Step("Удалить Sauce Labs Backpack из корзины")
    public CartPage removeBackpackFromCart() {
        removeBackpackButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public CartPage checkCartIsEmpty() {
        cartItems.shouldHave(
                com.codeborne.selenide.CollectionCondition.empty
        );
        return this;
    }

    @Step("Перейти к оформлению заказа")
    public CheckoutPage proceedToCheckout() {
        checkoutButton.shouldBe(visible).click();
        return new CheckoutPage();
    }
}
