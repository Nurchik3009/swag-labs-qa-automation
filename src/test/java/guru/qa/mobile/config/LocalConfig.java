package guru.qa.mobile.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config/mobile/local.properties")
public interface LocalConfig extends MobileConfig, Config {
}