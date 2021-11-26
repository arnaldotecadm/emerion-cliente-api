package br.com.arcasoftware.comercialapi.controller.relatorio;

import br.com.arcasoftware.comercialapi.application.service.relatorio.RelatorioService;
import br.com.arcasoftware.comercialapi.controller.relatorio.dto.CurvaABCVendedorDTO;
import br.com.arcasoftware.comercialapi.controller.relatorio.interfaces.ICurvaABCCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("relatorios")
@CrossOrigin("*")
public class Relatorios {

    private final RelatorioService service;

    @Autowired
    public Relatorios(RelatorioService service){
        this.service = service;
    }

    @GetMapping("curva-abc-clientes")
    public List<ICurvaABCCliente> getCurvaAbcCliente(){
        return this.service.getCurvaAbcCliente();
    }

    @GetMapping("curva-abc-vendedores")
    public List<CurvaABCVendedorDTO> getCurvaAbcVendedor(){
       return this.service.getCurvaAbcVendedor();
    }
}
