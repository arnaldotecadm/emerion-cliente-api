package br.com.arcasoftware.comercialapi.application.service.relatorio;

import br.com.arcasoftware.comercialapi.application.repository.ped.RelatorioRepository;
import br.com.arcasoftware.comercialapi.controller.relatorio.dto.CurvaABCVendedorDTO;
import br.com.arcasoftware.comercialapi.controller.relatorio.interfaces.ICurvaABCCliente;
import br.com.arcasoftware.comercialapi.controller.relatorio.interfaces.ICurvaABCVendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {
    private final RelatorioRepository repository;

    @Autowired
    public RelatorioService(RelatorioRepository repository) {
        this.repository = repository;
    }

    public List<ICurvaABCCliente> getCurvaAbcCliente() {
        return this.repository.getCurvaAbcCliente();
    }

    public List<CurvaABCVendedorDTO> getCurvaAbcVendedor() {
        BigDecimal totalGeralCurvaAbcVendedorFirstStep = this.getTotalGeralCurvaAbcVendedorFirstStep();
        BigDecimal totalGeralCurvaAbcVendedorSecondStep = this.getTotalGeralCurvaAbcVendedorSecondStep();
        List<ICurvaABCVendedor> curvaAbcVendedor = this.repository.getCurvaAbcVendedor();

        return curvaAbcVendedor.stream().map(item -> new CurvaABCVendedorDTO(item, totalGeralCurvaAbcVendedorFirstStep, totalGeralCurvaAbcVendedorSecondStep))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalGeralCurvaAbcVendedorFirstStep(){
        return this.repository.getTotalGeralCurvaAbcVendedorFirstStep();
    }

    public BigDecimal getTotalGeralCurvaAbcVendedorSecondStep(){
        return this.repository.getTotalGeralCurvaAbcVendedorSecondStep();
    }
}
