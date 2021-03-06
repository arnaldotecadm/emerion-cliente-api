package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.config.RoutingDataSource;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.model.DatasourceProperties;
import br.com.arcasoftware.comercialapi.model.StringEntity;
import br.com.arcasoftware.comercialapi.utils.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/dataset")
@CrossOrigin("*")
public class DatasetController {

    public static final String DEFAULT = "default";
    private final RoutingDataSource routingDataSource;
    private ApplicationProperties properties;

    private final Map<String, DatasourceProperties> datasourcePropertiesMap = new HashMap<>();

    @Autowired
    public DatasetController(RoutingDataSource routingDataSource, ApplicationProperties properties) {
        this.routingDataSource = routingDataSource;
        this.properties = properties;
    }

    @PostConstruct
    private void addDefaultDatasource() throws SQLException {
        Connection connection = null;
        try {
            DataSource ds = ((DataSource) routingDataSource.getDataSources().get(DEFAULT));
            connection = ds.getConnection();
            DatasourceProperties aDefault = new DatasourceProperties(
                    properties.getDriverClassName(),
                    properties.getHost(),
                    DEFAULT,
                    "5432",
                    properties.getDatabase(),
                    properties.getUsername(),
                    properties.getPassword());
            this.datasourcePropertiesMap.put(DEFAULT, aDefault);
            routingDataSource.addDataSource(DEFAULT, ds);
        } catch (Exception ex) {
            throw new ValidationException("Erro ao tentar adquirir os detalhes da conexao: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (null != connection) {
                connection.close();
            }
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<StringEntity> addDataSource(@RequestBody DatasourceProperties datasourceProperties) {
        this.datasourcePropertiesMap.put(datasourceProperties.getAlias(), datasourceProperties);
        routingDataSource.addDataSource(datasourceProperties.getAlias(), getDataSource(datasourceProperties));
        return new ResponseEntity<>(new StringEntity("Dataset adicionado com sucesso"), HttpStatus.OK);
    }

    @PostMapping("change-datasource/{alias}")
    public ResponseEntity<StringEntity> changeDataSource(@PathVariable("alias") String alias) {
        DatasourceProperties ds = this.datasourcePropertiesMap.get(alias);

        if (null == ds) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringEntity("Datasource nao encotrado"));
        }

        try {
            ((DataSource) this.routingDataSource.getDataSources().get(alias)).getConnection();
            this.routingDataSource.setKey(alias);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StringEntity("Erro ao tentar carregar a conex??o desejada: " + ex.getMessage()));
        }

        return new ResponseEntity<>(new StringEntity("Dataset alterado com sucesso"), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Collection<DatasourceProperties>> getAll() {
        return ResponseEntity.ok(this.datasourcePropertiesMap.values());
    }

    @GetMapping("current-alias")
    public ResponseEntity<DatasourceProperties> getCurrentDatasource() {
        return ResponseEntity.ok(this.datasourcePropertiesMap.get(this.routingDataSource.getKey()));
    }

    @GetMapping("/{alias}")
    public ResponseEntity<DatasourceProperties> getByAlias(@PathVariable("alias") String alias) {
        return ResponseEntity.ok(this.datasourcePropertiesMap.get(alias));
    }

    private DataSource getDataSource(DatasourceProperties datasourceProperties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(datasourceProperties.getDriver());
        dataSource.setUrl(String.format("jdbc:firebirdsql://%s:%s/%s",
                datasourceProperties.getHost(),
                datasourceProperties.getPort(),
                datasourceProperties.getUrl()));
        dataSource.setUsername(datasourceProperties.getUsername());
        dataSource.setPassword(datasourceProperties.getPassword());
        return dataSource;
    }
}