package br.com.arcasoftware.comercialapi.controller.est;

import br.com.arcasoftware.comercialapi.application.service.est.EstIcmService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Esticm;
import br.com.arcasoftwares.model.dto.IEstIcmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/esticm")
@CrossOrigin("*")
public class EstIcmController extends RestBasicController<Esticm, IEstIcmDTO> {

    private final EstIcmService service;

	@Autowired
	public EstIcmController(EstIcmService service) {
		super(service);
		this.service = service;
	}

	@GetMapping(value = "/{tipoIcms}/{codIcms}")
    public List<Esticm> getByTipoAndCodigo(@PathVariable("tipoIcms") String tipoIcms, @PathVariable("codIcms") String codIcms) {
        return service.getByTipoAndCodigo(tipoIcms, codIcms);
    }
}
