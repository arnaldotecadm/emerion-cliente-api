package br.com.arcasoftware.comercialapi.controller.est;

import br.com.arcasoftware.comercialapi.application.service.est.EstProService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Estpro;
import br.com.arcasoftwares.model.dto.IEstProDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estpro")
public class EstProController extends RestBasicController<Estpro, IEstProDTO> {

    private final EstProService service;

    @Autowired
    public EstProController(EstProService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = "all")
    public List<IEstProDTO> getAllEstpfa() {
        return service.getAll();
    }

}
