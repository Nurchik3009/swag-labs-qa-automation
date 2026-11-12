package guru.qa.mobile.tests;

import guru.qa.mobile.BaseTest;
import guru.qa.mobile.screens.LoginScreen;
import guru.qa.mobile.screens.ProductsScreen;
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

    private LoginScreen loginScreen;
    private ProductsScreen productsScreen;

    @BeforeEach
    void setUp() {
        loginScreen = new LoginScreen();
        productsScreen = new ProductsScreen();
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    void addProductToCartTest() {

        loginScreen.login("standard_user", "secret_sauce");

        productsScreen.addBackpackToCart();

        checkCartBadge("1");
    }

    @Step("Проверяем, что в корзине отображается {count} товар")
    void checkCartBadge(String count) {

        $(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + count + "\")"
        )).shouldBe(visible);
    }
}