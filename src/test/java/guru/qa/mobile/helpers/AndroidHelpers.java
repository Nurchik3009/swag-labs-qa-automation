
package guru.qa.mobile.helpers;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AndroidHelpers {

    public static void handleCompatibilityDialog() {

        SelenideElement okButton =
                $(AppiumBy.id("android:id/button2"));

        if (okButton.isDisplayed()) {
            okButton.shouldBe(visible).click();

            System.out.println(
                    "Android compatibility dialog was closed"
            );
        } else {
            System.out.println(
                    "Android compatibility dialog was not displayed"
            );
        }
    }
}
