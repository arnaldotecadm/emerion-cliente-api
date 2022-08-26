package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.model.AreaClienteDashboard;
import br.com.arcasoftware.comercialapi.model.DashBoardClienteInfo;
import br.com.arcasoftware.comercialapi.model.DashBoardCreditoInfo;
import br.com.arcasoftware.comercialapi.model.DashBoardEnderecoInfo;
import br.com.arcasoftware.comercialapi.application.service.AreaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("area-cliente")
@CrossOrigin("*")
public class AreaClienteController {

    private final AreaClienteService service;

    @Autowired
    public AreaClienteController(AreaClienteService service) {
        this.service = service;
    }

    @GetMapping("dashboard/{codcli}")
    public Object getDashBoard(@PathVariable("codcli") long codcli) {
        DashBoardClienteInfo dashBoardClienteInfo = this.service.getDashboardClienteInfo(codcli);
        List<DashBoardCreditoInfo> dashboardCreditoInfo = this.service.getDashboardCreditoInfo(codcli);
        List<DashBoardEnderecoInfo> dashboardEnderecoInfo = this.service.getDashboardEnderecoInfo(codcli);

        return new AreaClienteDashboard(dashBoardClienteInfo, dashboardCreditoInfo, dashboardEnderecoInfo);
    }

    @GetMapping("endereco-cliente/{codcli}")
    public Object getEnderecoCliente(@PathVariable("codcli") Integer codcli) {
        return this.service.getDashboardEnderecoCompleto(codcli);
    }

    @GetMapping("cliente/{codcli}")
    public Object getDashboardClienteInfoCompleto(@PathVariable("codcli") Integer codcli) {
        return this.service.getDashboardClienteInfoCompleto(codcli);
    }

    @GetMapping("credito-devolucao/{codcli}")
    public Object getDashboardCreditoDevolucao(@PathVariable("codcli") Integer codcli) {
        return this.service.getDashboardCreditoDevolucao(codcli);
    }
}
