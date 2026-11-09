
package guru.qa.manual;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.BLOCKER;

@Epic("Manual Testing")
@Feature("UI")
@Story("Complete checkout flow")
@Tag("manual")
public class ManualTests {

    @Test
    @DisplayName("TC-MANUAL-UI-001: Полный цикл покупки товара (Checkout Flow)")
    @Description("""
            Цель:
            Проверить возможность успешного оформления заказа
            авторизованным пользователем в интернет-магазине Swag Labs.

            Предусловия:
            - Приложение Swag Labs доступно.
            - Пользователь не авторизован в системе.
            - В каталоге доступен товар Sauce Labs Backpack.

            Тестовые данные:
            - Username: standard_user
            - Password: secret_sauce
            - Product: Sauce Labs Backpack
            - First Name: Test
            - Last Name: User
            - Postal Code: 12345

            Ожидаемый результат:
            - Пользователь успешно авторизован.
            - Открыта страница Products.
            - Товар Sauce Labs Backpack добавлен в корзину.
            - Счётчик корзины отображает количество 1.
            - В корзине отображается выбранный товар.
            - Пользователь успешно переходит к оформлению заказа.
            - Данные покупателя успешно принимаются системой.
            - Отображается страница завершения оформления заказа.
            - Заказ успешно оформлен.
            - Отображается сообщение "Thank you for your order!".
            """)
    @Severity(BLOCKER)
    void successfulCheckoutFlowManualTest() {

        Allure.step("1. Авторизация в системе", () -> {
            Allure.step("Открыть приложение Swag Labs.");
            Allure.step("Ввести логин standard_user.");
            Allure.step("Ввести пароль secret_sauce.");
            Allure.step("Нажать кнопку Login.");
            Allure.step("Проверить, что открылась страница Products.");
        });

        Allure.step("2. Добавление товара в корзину", () -> {
            Allure.step("Найти товар Sauce Labs Backpack.");
            Allure.step("Нажать кнопку Add to Cart.");
            Allure.step("Проверить, что счётчик корзины отображает 1.");
            Allure.step("Открыть корзину.");
        });

        Allure.step("3. Проверка корзины", () -> {
            Allure.step("Проверить наличие товара Sauce Labs Backpack.");
            Allure.step("Нажать кнопку Checkout.");
        });

        Allure.step("4. Заполнение информации о покупателе", () -> {
            Allure.step("Ввести First Name: Test.");
            Allure.step("Ввести Last Name: User.");
            Allure.step("Ввести Postal Code: 12345.");
            Allure.step("Нажать кнопку Continue.");
        });

        Allure.step("5. Завершение оформления заказа", () -> {
            Allure.step("Проверить информацию о заказе.");
            Allure.step("Нажать кнопку Finish.");
        });

        Allure.step("6. Проверка успешного оформления заказа", () -> {
            Allure.step("Проверить отображение страницы Checkout Complete.");
            Allure.step("Проверить сообщение: Thank you for your order!");
        });
    }
}
