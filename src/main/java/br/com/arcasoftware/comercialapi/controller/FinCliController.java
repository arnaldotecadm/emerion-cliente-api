package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.repository.model.dto.ClienteData;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.InformacaoTelaInicial;
import br.com.arcasoftware.comercialapi.application.service.FinCliService;
import br.com.arcasoftware.comercialapi.model.dto.IFinCliDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @PostMapping()
    public void save(@RequestBody @NotNull @Valid ClienteData clienteData){
        System.out.println(clienteData);
        this.service.save(clienteData);
    }

    @PostMapping("informacoes-tela-inicial")
    public void saveInformacoesTelaInicial(@RequestBody @NotNull @Valid InformacaoTelaInicial informacaoTelaInicial){
        System.out.println(informacaoTelaInicial);
        this.service.saveInformacaoTelaInicial(informacaoTelaInicial);
    }

}
