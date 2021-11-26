package br.com.arcasoftware.comercialapi.controller.est;

import br.com.arcasoftware.comercialapi.application.service.est.EstIpiService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Estipi;
import br.com.arcasoftwares.model.dto.IEstIpiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/estipi")
@CrossOrigin("*")
public class EstIpiController extends RestBasicController<Estipi, IEstIpiDTO> {

    private final EstIpiService service;

    @Autowired
    public EstIpiController(EstIpiService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = "{tipoIpi}/{codIpi}")
    public Estipi getByTipoAndCodigo(@PathVariable("tipoIpi") String tipoIpi, @PathVariable("codIpi") String codIpi) {
        return service.getByTipoAndCodigo(tipoIpi, codIpi);
    }
}
