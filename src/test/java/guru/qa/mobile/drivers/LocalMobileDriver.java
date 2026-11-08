
package guru.qa.mobile.drivers;

import guru.qa.mobile.config.LocalConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.net.URI;

public class LocalMobileDriver {

    public static AndroidDriver createDriver() throws Exception {

        LocalConfig config = ConfigFactory.create(LocalConfig.class);

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(config.platformName());
        options.setAutomationName("UiAutomator2");
        options.setDeviceName(config.deviceName());
        options.setPlatformVersion(config.platformVersion());

        options.setApp(new File(config.app()).getAbsolutePath());

        options.setCapability("appium:autoGrantPermissions", true);
        options.setCapability("appium:appWaitDuration", 60000);
        options.setCapability("appium:appWaitActivity", "*");
        options.setCapability("appium:androidInstallTimeout", 90000);

        return new AndroidDriver(
                URI.create("http://127.0.0.1:4723/wd/hub").toURL(),
                options
        );
    }
}
