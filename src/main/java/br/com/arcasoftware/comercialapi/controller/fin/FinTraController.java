package br.com.arcasoftware.comercialapi.controller.fin;

import br.com.arcasoftware.comercialapi.application.service.fin.FinTraService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Fintra;
import br.com.arcasoftwares.model.dto.IFinTraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "fintra")
@CrossOrigin("*")
public class FinTraController extends RestBasicController<Fintra, IFinTraDTO> {

    @Autowired
    public FinTraController(FinTraService service) {
        super(service);
    }

}
