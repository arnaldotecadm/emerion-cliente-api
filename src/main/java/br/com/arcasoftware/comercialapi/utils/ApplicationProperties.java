package br.com.arcasoftware.comercialapi.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class ApplicationProperties {
    private String url;
    private String driverClassName;
    private String username;
    private String password;

    public String getHost() {
        return this.getUrl().split(":")[2].substring(2);
    }

    public String getDatabase() {
        return this.getUrl().split(":")[3].split("/")[1];
    }
}
