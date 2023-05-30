package br.com.arcasoftware.comercialapi;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EntityScan(basePackageClasses = CustomerData.class)
@RestController
@EnableCaching
public class EmerionClienteApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmerionClienteApiApplication.class, args);
    }

    @GetMapping("")
    public String ping() {
        return "ok";
    }

}
