package br.com.arcasoftware.comercialapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DashBoardCreditoInfo {
    Integer getSeqcde();

    LocalDateTime getDtecde();

    String getTipo();

    BigDecimal getValor();
}