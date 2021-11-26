package br.com.arcasoftware.comercialapi.controller.fin;

import br.com.arcasoftware.comercialapi.application.service.fin.FinCliService;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicController;
import br.com.arcasoftwares.model.Fincli;
import br.com.arcasoftwares.model.dto.FinCliDTO;
import br.com.arcasoftwares.model.dto.IFinCliDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "fincli")
@CrossOrigin("*")
public class FinCliController extends RestBasicController<Fincli, IFinCliDTO> {

    private final FinCliService service;

    @Autowired
    public FinCliController(FinCliService service) {
        super(service);
        this.service = service;
    }

    @PostMapping(value = "filter", consumes = "application/json")
    public List<FinCliDTO> getByFilter(@RequestBody FinCliDTO finCliDTO) {
        List<Fincli> fincliList = service.getByFilter(finCliDTO);
        List<FinCliDTO> fincliDTOlist = new ArrayList<>();
        fincliList.forEach(fincli -> fincliDTOlist.add(new FinCliDTO(fincli)));
        return fincliDTOlist;
    }
}
