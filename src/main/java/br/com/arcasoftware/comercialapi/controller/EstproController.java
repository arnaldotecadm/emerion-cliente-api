package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.repository.model.Estpro;
import br.com.arcasoftware.comercialapi.application.service.EstproService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/estpro")
@CrossOrigin("*")
public class EstproController {

    private final EstproService service;

    @Autowired
    public EstproController(EstproService service) {
        this.service = service;
    }

    @PostMapping("")
    public Estpro save(@RequestBody @NotNull @Valid Estpro data) {
        return this.service.save(data);
    }
}
