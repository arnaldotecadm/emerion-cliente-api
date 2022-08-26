package br.com.arcasoftware.comercialapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public interface DashBoardCreditoInfo {
    Integer getSeqcde();

    String getDtecde();

    String getTipo();

    BigDecimal getValor();
}
