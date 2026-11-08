
package guru.qa.ui.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/web/web.properties"
})
public interface WebConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://www.saucedemo.com")
    String baseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
    @DefaultValue("")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("")
    String remoteUrl();
}
