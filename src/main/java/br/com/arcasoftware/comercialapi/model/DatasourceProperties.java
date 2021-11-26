package br.com.arcasoftware.comercialapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DatasourceProperties {
    private String driver;
    private String host;
    private String alias;
    private String port;
    private String url;
    private String username;
    private String password;
}