package br.com.arcasoftware.comercialapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public interface DashBoardCreditoDevolucao {
    Integer getSeqcde();

    String getDTECDE();

    BigDecimal getVALCDE();

    BigDecimal getUSACDE();

    BigDecimal getSLDCDE();

    LocalDateTime getDTEPED();

    String getOBSCDE();

    String getSITCDE();

    String getTipo();
}
