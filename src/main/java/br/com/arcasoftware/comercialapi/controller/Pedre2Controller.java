package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.repository.model.Pedre2;
import br.com.arcasoftware.comercialapi.application.repository.model.Pedres;
import br.com.arcasoftware.comercialapi.application.service.PedResService;
import br.com.arcasoftware.comercialapi.application.service.Pedre2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/pedre2")
@CrossOrigin("*")
public class Pedre2Controller {

    private final Pedre2Service service;

    @Autowired
    public Pedre2Controller(Pedre2Service service) {
        this.service = service;
    }

    @PostMapping("")
    public void save(@RequestBody @NotNull @Valid Pedre2 data) {
        this.service.save(data);
    }
}
