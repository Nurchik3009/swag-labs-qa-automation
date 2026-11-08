package guru.qa.mobile;

import com.codeborne.selenide.WebDriverRunner;
import guru.qa.mobile.drivers.BrowserstackMobileDriver;
import guru.qa.mobile.drivers.LocalMobileDriver;
import guru.qa.mobile.helpers.AndroidHelpers;
import guru.qa.mobile.helpers.Attachments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Locale;

public class BaseTest {

    protected static String deviceHost;

    @BeforeAll
    static void setUp() {
        deviceHost = System.getProperty("deviceHost", "local")
                .toLowerCase(Locale.ROOT);

        if (!deviceHost.equals("local")
                && !deviceHost.equals("browserstack")) {
            throw new IllegalArgumentException(
                    "Unsupported deviceHost: " + deviceHost
            );
        }
    }

    protected void startDriver() throws Exception {

        if (deviceHost.equals("browserstack")) {
            WebDriverRunner.setWebDriver(
                    BrowserstackMobileDriver.createDriver()
            );
        } else {
            WebDriverRunner.setWebDriver(
                    LocalMobileDriver.createDriver()
            );
        }
    }

    @BeforeEach
    void startTest() throws Exception {
        startDriver();
        AndroidHelpers.handleCompatibilityDialog();
    }


    @AfterEach
    void tearDown() {

        if (!WebDriverRunner.hasWebDriverStarted()) {
            return;
        }

        String sessionId = null;

        try {
            // Сначала делаем скриншот и получаем Page Source
            Attachments.screenshot();
            Attachments.pageSource();

            if (deviceHost.equals("browserstack")) {

                RemoteWebDriver driver =
                        (RemoteWebDriver) WebDriverRunner.getWebDriver();

                sessionId = driver.getSessionId().toString();

                Attachments.attachAsText(
                        "BrowserStack session ID",
                        sessionId
                );
            }

        } catch (Exception e) {
            Attachments.attachAsText(
                    "Attachment error",
                    e.toString()
            );

        } finally {
            // Сначала закрываем сессию
            WebDriverRunner.closeWebDriver();
        }

        // Только после закрытия сессии скачиваем видео
        if (sessionId != null) {
            Attachments.addVideo(sessionId);
        }
    }
}