package br.com.arcasoftware.comercialapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AreaClienteDashboard {
    private final DashBoardClienteInfo dashBoardClienteInfo;
    private final List<DashBoardCreditoInfo> dashBoardCreditoInfo;
    private final List<DashBoardEnderecoInfo> dashboardEnderecoInfo;
}
