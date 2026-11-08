
package guru.qa.mobile.drivers;

import guru.qa.mobile.config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackMobileDriver {

    public static AndroidDriver createDriver() throws Exception {

        BrowserstackConfig config =
                ConfigFactory.create(BrowserstackConfig.class);

        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        if (username == null || username.isBlank()) {
            username = config.browserstackUser();
        }

        if (accessKey == null || accessKey.isBlank()) {
            accessKey = config.browserstackKey();
        }

        if (username == null || username.isBlank()
                || accessKey == null || accessKey.isBlank()) {
            throw new IllegalStateException(
                    "BrowserStack credentials are missing. " +
                            "Set BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY."
            );
        }

        Map<String, Object> browserstackOptions = new HashMap<>();

        browserstackOptions.put("userName", username);
        browserstackOptions.put("accessKey", accessKey);
        browserstackOptions.put("deviceName", config.deviceName());
        browserstackOptions.put("osVersion", config.platformVersion());
        browserstackOptions.put("projectName", "Swag Labs QA Automation");
        browserstackOptions.put("buildName", "Mobile Tests");
        browserstackOptions.put("sessionName", "Swag Labs Android Test");

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(config.platformName());
        options.setAutomationName("UiAutomator2");
        options.setApp(config.app());

        options.setCapability("bstack:options", browserstackOptions);

        return new AndroidDriver(
                URI.create(config.remoteUrl()).toURL(),
                options
        );
    }
}
