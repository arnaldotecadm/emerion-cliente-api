package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.service.FinCliService;
import br.com.arcasoftware.comercialapi.model.dto.IFinCliDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "fincli")
@CrossOrigin("*")
public class FinCliController {

    private final FinCliService service;

    @Autowired
    public FinCliController(FinCliService service) {
        this.service = service;
    }

    @GetMapping(value = {"", "all"})
    public List<IFinCliDTO> getAllDTO() {
        return this.service.getAllDTO();
    }

}
