package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.repository.model.Fintra;
import br.com.arcasoftware.comercialapi.application.repository.model.Finven;
import br.com.arcasoftware.comercialapi.application.repository.model.Geremp;
import br.com.arcasoftware.comercialapi.application.service.FintraService;
import br.com.arcasoftware.comercialapi.application.service.FinvenService;
import br.com.arcasoftware.comercialapi.application.service.GerempService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "auxiliares")
@CrossOrigin("*")
public class CadastrosAuxiliaresController {

    private final FinvenService finvenService;
    private final GerempService gerempService;
    private final FintraService fintraService;

    public CadastrosAuxiliaresController(FinvenService finvenService, GerempService gerempService, FintraService fintraService) {
        this.finvenService = finvenService;
        this.gerempService = gerempService;
        this.fintraService = fintraService;
    }

    @PostMapping("finven")
    public void saveFinven(@RequestBody @NotNull @Valid Finven finven) {
        System.out.println(finven);
        this.finvenService.save(finven);
    }

    @PostMapping("geremp")
    public void saveGeremp(@RequestBody @NotNull @Valid Geremp geremp) {
        System.out.println(geremp);
        this.gerempService.save(geremp);
    }

    @PostMapping("fintra")
    public void saveFintra(@RequestBody @NotNull @Valid Fintra fintra) {
        System.out.println(fintra);
        this.fintraService.save(fintra);
    }
}
