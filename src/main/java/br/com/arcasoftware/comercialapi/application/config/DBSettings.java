package br.com.arcasoftware.comercialapi.application.config;

public interface DBSettings {

    int getPort();

    String getServer();

    String getSelectedDataBaseName();

    String getPassword();

    String getUserName();

    String dbmsType();

    String jDBConnectionURL();

    String driverClassName();

    String dialect();
}
