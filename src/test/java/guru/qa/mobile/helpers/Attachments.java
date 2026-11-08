package guru.qa.mobile.helpers;

import com.codeborne.selenide.WebDriverRunner; import io.qameta.allure.Allure; import org.openqa.selenium.OutputType; import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream; import java.nio.charset.StandardCharsets;

public class Attachments {


    public static void screenshot() {
        try {
            byte[] screenshot =
                    ((TakesScreenshot) WebDriverRunner.getWebDriver())
                            .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );
        } catch (Exception e) {
            attachAsText("Screenshot error", e.toString());
        }
    }

    public static void pageSource() {
        try {
            String source = WebDriverRunner.getWebDriver().getPageSource();

            Allure.addAttachment(
                    "Page Source",
                    "text/xml",
                    source,
                    ".xml"
            );
        } catch (Exception e) {
            attachAsText("Page Source error", e.toString());
        }
    }

    public static void attachAsText(String name, String message) {
        Allure.addAttachment(name, "text/plain", message);
    }

    public static void addVideo(String sessionId) {
        String videoUrl =
                "https://app-automate.browserstack.com/dashboard/v2/sessions/"
                        + sessionId;

        String html = "<html><body>" +
                "<a href='" + videoUrl + "' target='_blank'>" +
                "Open BrowserStack video</a>" +
                "</body></html>";

        Allure.addAttachment(
                "BrowserStack Video",
                "text/html",
                html,
                ".html"
        );
    }
}