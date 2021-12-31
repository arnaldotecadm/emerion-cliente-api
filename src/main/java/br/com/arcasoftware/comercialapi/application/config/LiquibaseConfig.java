package br.com.arcasoftware.comercialapi.application.config;

import br.com.arcasoftware.comercialapi.utils.PropertyUtils;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Configuration
public class LiquibaseConfig {

    private final DataSource dataSource;
    private final PropertyUtils propertyUtils;

    public LiquibaseConfig(DataSource dataSource, PropertyUtils propertyUtils){
        this.dataSource = dataSource;
        this.propertyUtils = propertyUtils;
    }

    @Bean
    @DependsOn(value = "entityManagerFactory")
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/changelog-master.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setShouldRun(propertyUtils.isShouldRunLiquibase());
        return liquibase;
    }
}
