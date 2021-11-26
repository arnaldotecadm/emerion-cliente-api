package br.com.arcasoftware.comercialapi.controller.areacliente.service;

import br.com.arcasoftware.comercialapi.controller.areacliente.model.*;
import br.com.arcasoftware.comercialapi.controller.areacliente.repository.AreaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaClienteService {

    private final AreaClienteRepository repository;

    @Autowired
    public AreaClienteService(AreaClienteRepository repository) {
        this.repository = repository;
    }

    public DashBoardClienteInfo getDashboardClienteInfo(Integer codcli) {
        return this.repository.getDashboardClienteInfo(codcli);
    }

    public List<DashBoardCreditoInfo> getDashboardCreditoInfo(Integer codcli) {
        return this.repository.getDashboardCreditoInfo(codcli);
    }

    public List<DashBoardEnderecoInfo> getDashboardEnderecoInfo(Integer codcli) {
        return this.repository.getDashboardEnderecoInfo(codcli);
    }

    public List<DashBoardEnderecoCompleto> getDashboardEnderecoCompleto(Integer codcli) {
        return this.repository.getDashboardEnderecoCompleto(codcli);
    }

    public DashBoardClienteInfoCompleto getDashboardClienteInfoCompleto(Integer codcli) {
        return this.repository.getDashboardClienteInfoCompleto(codcli);
    }

    public List<DashBoardCreditoDevolucao> getDashboardCreditoDevolucao(Integer codcli) {
        return this.repository.getDashboardCreditoDevolucao(codcli);
    }
}
