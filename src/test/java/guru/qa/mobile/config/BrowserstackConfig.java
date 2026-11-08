package guru.qa.mobile.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config/mobile/browserstack.properties")
public interface BrowserstackConfig extends MobileConfig, Config {

    @Key("browserstack.user")
    String browserstackUser();

    @Key("browserstack.key")
    String browserstackKey();

    String remoteUrl();
}