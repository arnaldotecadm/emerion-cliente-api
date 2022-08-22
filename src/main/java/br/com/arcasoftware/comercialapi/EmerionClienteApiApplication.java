package br.com.arcasoftware.comercialapi;

import br.com.arcasoftware.comercialapi.model.Pedres;
import br.com.arcasoftware.comercialapi.utils.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EntityScan(basePackageClasses = Pedres.class)
@RestController
@EnableCaching
public class EmerionClienteApiApplication {

    @Autowired
    private ApplicationProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(EmerionClienteApiApplication.class, args);
    }

    @GetMapping("")
    public String ping() {
        return "ok";
    }

}
