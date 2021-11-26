package br.com.arcasoftware.comercialapi.application.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Bean
    @Qualifier("default")
    @ConfigurationProperties(prefix = "spring.datasource")
    protected DataSource defaultDataSource(){
        return DataSourceBuilder
                .create()
                .url(this.dataSourceUrl)
                .username(this.username)
                .password(this.password)
                .driverClassName(this.driverClassName)
                .build();
    }

    @Bean
    @Primary
    @Scope("singleton")
    public AbstractRoutingDataSource routingDataSource(@Autowired @Qualifier("default") @Lazy DataSource defaultDataSource){
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.addDataSource(RoutingDataSource.DEFAULT,defaultDataSource);
        routingDataSource.setDefaultTargetDataSource(defaultDataSource);
        return routingDataSource;
    }

}
