package br.com.arcasoftware.comercialapi.controller.fin;

import br.com.arcasoftware.comercialapi.application.service.fin.FinVenService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Finven;
import br.com.arcasoftwares.model.dto.IFinVenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "finven")
@CrossOrigin("*")
public class FinVenController extends RestBasicController<Finven, IFinVenDTO> {

    @Autowired
    public FinVenController(FinVenService service) {
        super(service);
    }

}
