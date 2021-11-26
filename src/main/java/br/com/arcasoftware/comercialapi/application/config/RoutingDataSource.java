package br.com.arcasoftware.comercialapi.application.config;

import lombok.Getter;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {

    static final String DEFAULT = "default";

    private String key = DEFAULT;

    public void setKey(String key){
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Getter
    private final Map<Object,Object> dataSources = new HashMap<>();

    RoutingDataSource() {
        setTargetDataSources(dataSources);
    }

    public void addDataSource(String key, DataSource dataSource){
        dataSources.put(key,dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return key;
    }

    @Override
    public DataSource determineTargetDataSource() {
        return (DataSource) dataSources.get(key);
    }

}
