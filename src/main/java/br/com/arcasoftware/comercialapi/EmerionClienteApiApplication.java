package br.com.arcasoftware.comercialapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arcasoftwares.model.Pedres;

@SpringBootApplication
@EntityScan(basePackageClasses = Pedres.class)
@RestController
@EnableCaching
public class EmerionClienteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmerionClienteApiApplication.class, args);
	}

	@GetMapping("")
	public String ping(){
		return "ok";
	}

}
