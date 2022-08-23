package br.com.arcasoftware.comercialapi.application.repository.model.dto;

import br.com.arcasoftware.comercialapi.application.repository.model.TipIndicIe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DashboardCliente {
    private Regtrib regtrib;
    private TipIndicIe indicIe;
}
