package br.com.arcasoftware.comercialapi.application.config;

public class LocalDataBaseSettings implements DBSettings {
    @Override
    public int getPort() {
        return 3050;
    }

    @Override
    public String getServer() {
        return null;
    }

    @Override
    public String getSelectedDataBaseName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String dbmsType() {
        return null;
    }

    @Override
    public String jDBConnectionURL() {
        return null;
    }

    @Override
    public String driverClassName() {
        return null;
    }

    @Override
    public String dialect() {
        return null;
    }
}
