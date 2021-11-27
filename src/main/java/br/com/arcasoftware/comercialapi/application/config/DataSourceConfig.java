package br.com.arcasoftware.comercialapi.application.config;
import br.com.arcasoftware.comercialapi.utils.ApplicationProperties;
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

    private ApplicationProperties properties;

    public DataSourceConfig(ApplicationProperties properties){
        this.properties = properties;
    }

    @Bean
    @Qualifier("default")
    @ConfigurationProperties(prefix = "spring.datasource")
    protected DataSource defaultDataSource(){
        return DataSourceBuilder
                .create()
                .url(this.properties.getUrl())
                .username(this.properties.getUsername())
                .password(this.properties.getPassword())
                .driverClassName(this.properties.getDriverClassName())
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
