
package guru.qa.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {

    private final SelenideElement title = $(".title");
    private final ElementsCollection products = $$(".inventory_item");
    private final ElementsCollection productPrices = $$(".inventory_item_price");
    private final SelenideElement sortDropdown = $(".product_sort_container");
    private final SelenideElement cartBadge = $(".shopping_cart_badge");
    private final SelenideElement cartLink = $(".shopping_cart_link");
    private final SelenideElement menuButton = $("#react-burger-menu-btn");
    private final SelenideElement logoutButton = $("#logout_sidebar_link");

    private final SelenideElement backpackAddButton =
            $("#add-to-cart-sauce-labs-backpack");

    @Step("Проверить заголовок страницы: {expectedTitle}")
    public ProductsPage checkTitle(String expectedTitle) {
        title.shouldBe(visible)
                .shouldHave(exactText(expectedTitle));
        return this;
    }

    @Step("Проверить количество товаров в каталоге: {expectedCount}")
    public ProductsPage checkProductsCount(int expectedCount) {
        products.shouldHave(com.codeborne.selenide.CollectionCondition.size(expectedCount));
        return this;
    }

    @Step("Отсортировать товары по возрастанию цены")
    public ProductsPage sortProductsByPriceLowToHigh() {
        sortDropdown.selectOptionByValue("lohi");
        return this;
    }

    @Step("Проверить цену первого товара: {expectedPrice}")
    public ProductsPage checkFirstProductPrice(String expectedPrice) {
        productPrices.first()
                .shouldBe(visible)
                .shouldHave(exactText(expectedPrice));
        return this;
    }

    @Step("Добавить товар в корзину")
    public ProductsPage addBackpackToCart() {
        backpackAddButton.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить количество товаров в корзине: {expectedCount}")
    public ProductsPage checkCartBadge(String expectedCount) {
        cartBadge.shouldBe(visible)
                .shouldHave(exactText(expectedCount));
        return this;
    }

    @Step("Открыть корзину")
    public CartPage openCart() {
        cartLink.shouldBe(visible).click();
        return new CartPage();
    }

    @Step("Выйти из аккаунта")
    public LoginPage logout() {
        menuButton.click();
        logoutButton.shouldBe(visible).click();
        return new LoginPage();
    }
}
