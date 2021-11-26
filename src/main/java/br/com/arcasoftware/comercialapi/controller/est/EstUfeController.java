package br.com.arcasoftware.comercialapi.controller.est;

import br.com.arcasoftware.comercialapi.application.service.est.EstUfeService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Estufe;
import br.com.arcasoftwares.model.dto.IEstUfeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/estufe")
@CrossOrigin("*")
public class EstUfeController extends RestBasicController<Estufe, IEstUfeDTO> {

    private final EstUfeService service;

	@Autowired
	public EstUfeController(EstUfeService service) {
		super(service);
		this.service = service;
	}

	@GetMapping(value = "/{tipo}/{codigo}")
	public List<IEstUfeDTO> getByTipoAndCodigo(@PathVariable("tipo") String tipo, @PathVariable("codigo") String codigo) {
		return service.getAllByTipoAndCodigo(tipo, codigo);
	}
}
