package br.com.arcasoftware.comercialapi.model;

import java.math.BigDecimal;

public interface IPedResCab {

    Integer getCodcli();

    BigDecimal getNUMRES();

    BigDecimal getTOTRES();

    BigDecimal getTOTIPI();

    BigDecimal getTOTSUB();

    BigDecimal getTOTDESCINC();

    BigDecimal getTOTGER();

    BigDecimal getTOTREN();

    String getObsres();

    String getPedant();
}
