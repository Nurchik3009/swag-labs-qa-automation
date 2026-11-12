package guru.qa.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.ui.config.WebConfig;
import guru.qa.ui.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected static final WebConfig config =
            ConfigFactory.create(WebConfig.class, System.getProperties());

    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.browserSize();

        boolean isRemote = Boolean.parseBoolean(
                System.getProperty(
                        "isRemote",
                        String.valueOf(config.isRemote())
                )
        );

        String remoteUrl = System.getProperty(
                "remoteUrl",
                config.remoteUrl()
        );

        if (isRemote) {

            if (remoteUrl == null || remoteUrl.isBlank()) {
                throw new IllegalStateException(
                        "Remote URL is not configured!"
                );
            }

            Configuration.remote = remoteUrl;

            ChromeOptions chromeOptions = new ChromeOptions();

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", true);
            selenoidOptions.put("sessionTimeout", "2m");

            chromeOptions.setCapability(
                    "selenoid:options",
                    selenoidOptions
            );

            Configuration.browserCapabilities = chromeOptions;

        } else {
            Configuration.remote = null;
        }

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
    }

    @BeforeEach
    void addListener() {

        SelenideLogger.removeListener("AllureSelenide");

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterEach
    void addAttachments() {

        try {

            if (Selenide.webdriver().driver().hasWebDriverStarted()) {

                Attach.screenshotAs("Last screenshot");
                Attach.pageSource();
                Attach.browserConsoleLogs();

                boolean isRemote = Boolean.parseBoolean(
                        System.getProperty(
                                "isRemote",
                                String.valueOf(config.isRemote())
                        )
                );

                if (isRemote) {
                    Attach.addVideo();
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Не удалось добавить вложения в Allure: "
                            + e.getMessage()
            );

        } finally {

            Selenide.closeWebDriver();
        }
    }
}