package guru.qa.ui.tests;

import guru.qa.ui.BaseTest;
import guru.qa.ui.TestData;
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
@Feature("Products catalog")
@Owner("Ainur Kaliakbarova")
@Tag("ui")
public class ProductsTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();

    @Test
    @Story("Products display")
    @DisplayName("Успешное отображение каталога товаров")
    @Severity(SeverityLevel.CRITICAL)
    void productsPageDisplayedTest() {
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        productsPage.checkTitle(TestData.PRODUCTS_PAGE_TITLE)
                .checkProductsCount(TestData.EXPECTED_PRODUCTS_COUNT);
    }

    @Test
    @Story("Products sorting")
    @DisplayName("Сортировка товаров по возрастанию цены")
    @Severity(SeverityLevel.NORMAL)
    void sortProductsByPriceTest() {
        loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);
        productsPage.sortProductsByPriceLowToHigh()
                .checkFirstProductPrice(TestData.CHEAPEST_PRODUCT_PRICE);
    }
}