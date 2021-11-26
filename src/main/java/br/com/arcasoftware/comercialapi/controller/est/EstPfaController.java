package br.com.arcasoftware.comercialapi.controller.est;

import br.com.arcasoftware.comercialapi.application.service.est.EstPfaService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Estpfa;
import br.com.arcasoftwares.model.dto.EstPfaDTO;
import br.com.arcasoftwares.model.dto.IEstPfaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/estpfa")
@CrossOrigin("*")
public class EstPfaController extends RestBasicController<Estpfa, IEstPfaDTO> {

    private final EstPfaService service;

    @Autowired
    public EstPfaController(EstPfaService service) {
        super(service);this.service = service;
    }

    @GetMapping(value = "{tipo}/{codpfa}")
    public Estpfa getByCodPfa(@PathVariable("tipo") String tipo, @PathVariable("codpfa") String codpfa) {
        EstPfaDTO estPfaDTO = new EstPfaDTO(codpfa, tipo, "");
        return service.getById(estPfaDTO);
    }

}
