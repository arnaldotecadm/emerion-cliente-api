package br.com.arcasoftware.comercialapi.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class PropertyUtils {

    private String url;
    private String driverClassName;
    private String username;
    private String password;

    @Value("${spring.jpa.database-platform}")
    private String databasePlatform;

    @Value("${spring.liquibase.enabled}")
    private boolean shouldRunLiquibase;

}
