package br.com.arcasoftware.comercialapi.controller.relatorio.interfaces;

import java.math.BigDecimal;

public interface ICurvaABCVendedor {
    Integer getCODVEN();

    String getAPEVEN();

    BigDecimal getTOTLIQ();

    BigDecimal getTOTBRT();

    BigDecimal getCSTPRT();

    BigDecimal getCSTPFI();

    BigDecimal getCSTVME();

    BigDecimal getDSCPER();

    BigDecimal getDSCPRA();

    BigDecimal getDIFDSC();

    BigDecimal getMARPRE();

    BigDecimal getMARPED();

    BigDecimal getLUCROL();

    BigDecimal getLUCROP();

    BigDecimal getTOTNAT();

    BigDecimal getTOTPER();

    BigDecimal getTOTFAT();

    BigDecimal getCSTFAT();
}
