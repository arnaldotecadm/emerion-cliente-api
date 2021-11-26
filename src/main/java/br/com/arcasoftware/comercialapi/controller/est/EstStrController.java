package br.com.arcasoftware.comercialapi.controller.est;

import br.com.arcasoftware.comercialapi.application.service.est.EstStrService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Eststr;
import br.com.arcasoftwares.model.dto.IEstStrDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/eststr")
@CrossOrigin("*")
public class EstStrController extends RestBasicController<Eststr, IEstStrDTO> {

	@Autowired
	public EstStrController(EstStrService service) {
		super(service);
	}
}
