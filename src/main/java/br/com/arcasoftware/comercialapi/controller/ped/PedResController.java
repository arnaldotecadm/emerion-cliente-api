package br.com.arcasoftware.comercialapi.controller.ped;

import br.com.arcasoftware.comercialapi.application.service.ReportService;
import br.com.arcasoftware.comercialapi.application.service.ped.PedResService;
import br.com.arcasoftwares.model.dto.IPedRe2DTO;
import br.com.arcasoftwares.model.dto.IPedResCab;
import br.com.arcasoftwares.model.dto.IPedResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pedres")
@CrossOrigin("*")
public class PedResController {

    private final PedResService service;

    @Autowired
    private ReportService reportService;

    @Autowired
    public PedResController(PedResService service) {
        this.service = service;
    }

    @GetMapping(value = {"cabecalho-pedido"})
    public List<IPedResDTO> getCabecalhoPedidoList() {
        return service.getCabecalhoPedidoList(99999);
    }

    @GetMapping(value = {"cabecalho-pedido/limit/{recordCount}"})
    public List<IPedResDTO> getCabecalhoPedidoListWithLimit(@PathVariable("recordCount") Integer recordCount) {
        return service.getCabecalhoPedidoList(recordCount);
    }

    @GetMapping(value = {"cabecalho-pedido-cliente/{codcli}"})
    public List<IPedResDTO> getCabecalhoPedidoListByCliente(@PathVariable("codcli") Integer codcli) {
        return service.getCabecalhoPedidoListByCodcli(codcli);
    }

    @GetMapping(value = {"detalhe-pedido/{numres}"})
    public List<IPedRe2DTO> getDetalhesPedido(@PathVariable("numres") String numres) {
        return service.getDetalhesPedido(numres);
    }

    @GetMapping(value = {"cabecalho-pedido/{numres}"})
    public IPedResCab getCabecalhoPedido(@PathVariable("numres") String numres) {
        return service.getCabecalhoPedido(numres);
    }

}
