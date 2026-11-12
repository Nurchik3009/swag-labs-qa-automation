package guru.qa.ui.tests;

import guru.qa.ui.BaseTest;
import guru.qa.ui.TestData;
import guru.qa.ui.pages.CartPage;
import guru.qa.ui.pages.CheckoutPage;
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
@Feature("Shopping cart and checkout")
@Owner("Ainur Kaliakbarova")
@Tag("ui")
public class CartTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();

    @Test
    @Story("Add product to cart")
    @DisplayName("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    void addProductToCartTest() {
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        productsPage.addBackpackToCart()
                .checkCartBadge(TestData.ONE_PRODUCT_IN_CART)
                .openCart();
        cartPage.checkItemInCart(TestData.BACKPACK_NAME);
    }

    @Test
    @Story("Remove product from cart")
    @DisplayName("Удаление товара из корзины")
    @Severity(SeverityLevel.NORMAL)
    void removeProductFromCartTest() {
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        productsPage.addBackpackToCart()
                .openCart();
        cartPage.removeBackpackFromCart()
                .checkCartIsEmpty();
    }

    @Test
    @Story("Complete checkout flow")
    @DisplayName("Полный цикл покупки товара (Checkout Flow)")
    @Severity(SeverityLevel.BLOCKER)
    void successfulCheckoutFlowTest() {
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        productsPage.checkTitle(TestData.PRODUCTS_PAGE_TITLE)
                .addBackpackToCart()
                .checkCartBadge(TestData.ONE_PRODUCT_IN_CART)
                .openCart();

        cartPage.checkItemInCart(TestData.BACKPACK_NAME)
                .proceedToCheckout();

        checkoutPage.fillCustomerInfo(
                        TestData.CUSTOMER_FIRST_NAME,
                        TestData.CUSTOMER_LAST_NAME,
                        TestData.CUSTOMER_POSTAL_CODE
                )
                .clickContinue()
                .clickFinish()
                .checkSuccessMessage(TestData.ORDER_SUCCESS_MESSAGE);
    }
}