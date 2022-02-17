package br.com.arcasoftware.comercialapi.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

    public static void main(String[] args) {
        String json = "{\"cnpjEmitente\":\"null\",\"fileName\":\"35160107022495000107550010000666471979472972-nfe.xml\"}";
        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
        System.out.println(convertedObject);
    }
}
