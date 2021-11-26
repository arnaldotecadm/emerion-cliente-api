package br.com.arcasoftware.comercialapi.controller.areacliente.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DashBoardCreditoDevolucao {
    Integer getSeqcde();

    BigDecimal getVALCDE();

    BigDecimal getUSACDE();

    BigDecimal getSLDCDE();

    LocalDateTime getDTEPED();

    String getOBSCDE();

    String getSITCDE();

    String getTipo();
}
