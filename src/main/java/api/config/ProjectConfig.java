package api.config;

import org.aeonbits.owner.Config;


@Config.Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

    @Key("apiurl")
    String apiurl();

    @Key("apiusername")
    String apiusername();

    @Key("apipassword")
    String apipassword();

    @Key("username")
    String username();

}
