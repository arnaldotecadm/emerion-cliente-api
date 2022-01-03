package br.com.arcasoftware.comercialapi.model;

import java.math.BigDecimal;
import java.util.Date;

public interface DashBoardCreditoInfo {
    Integer getSeqcde();

    Date getDtecde();

    String getTipo();

    BigDecimal getValor();
}
