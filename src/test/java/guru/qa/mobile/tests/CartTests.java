package guru.qa.mobile.tests;

import guru.qa.mobile.BaseTest;
import guru.qa.mobile.pages.LoginPage;
import guru.qa.mobile.pages.ProductsPage;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Tag("mobile")
public class CartTests extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    void addProductToCartTest() {

        loginPage.login("standard_user", "secret_sauce");

        productsPage.addBackpackToCart();

        checkCartBadge("1");
    }

    @Step("Проверяем, что в корзине отображается {count} товар")
    void checkCartBadge(String count) {

        $(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + count + "\")"
        )).shouldBe(visible);
    }
}