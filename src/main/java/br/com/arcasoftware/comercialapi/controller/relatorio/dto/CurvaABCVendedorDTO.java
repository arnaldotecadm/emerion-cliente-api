package br.com.arcasoftware.comercialapi.controller.relatorio.dto;

import br.com.arcasoftware.comercialapi.controller.relatorio.interfaces.ICurvaABCVendedor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class CurvaABCVendedorDTO {

    private final Integer codven;
    private final String apeven;
    private final BigDecimal totliq;
    private final BigDecimal totbrt;
    private final BigDecimal cstprt;
    private final BigDecimal cstpfi;
    private final BigDecimal cstvme;
    private final BigDecimal dscper;
    private final BigDecimal dscpra;
    private final BigDecimal difdsc;
    private final BigDecimal marpre;
    private final BigDecimal marped;
    private final BigDecimal lucrol;
    private final BigDecimal lucrop;
    private final BigDecimal totnat;
    private final BigDecimal decimal;
    private final BigDecimal totfat;
    private final BigDecimal cstfat;
    private final BigDecimal prtfat;
    private final BigDecimal prtcst;

    public CurvaABCVendedorDTO(ICurvaABCVendedor abcVendedor, BigDecimal totalGeral01, BigDecimal totalGeral02) {
        this.codven = abcVendedor.getCODVEN();
        this.apeven = abcVendedor.getAPEVEN();
        this.totliq = abcVendedor.getTOTLIQ();
        this.totbrt = abcVendedor.getTOTBRT();
        this.cstprt = abcVendedor.getCSTPRT();
        this.cstpfi = abcVendedor.getCSTPFI();
        this.cstvme = abcVendedor.getCSTVME();
        this.dscper = abcVendedor.getDSCPER();
        this.dscpra = abcVendedor.getDSCPRA();
        this.difdsc = abcVendedor.getDIFDSC();
        this.marpre = abcVendedor.getMARPRE();
        this.marped = abcVendedor.getMARPED();
        this.lucrol = abcVendedor.getLUCROL();
        this.lucrop = abcVendedor.getLUCROP();
        this.totnat = abcVendedor.getTOTNAT();
        this.decimal = abcVendedor.getTOTPER();
        this.totfat = abcVendedor.getTOTFAT();
        this.cstfat = abcVendedor.getCSTFAT();
        this.prtfat = abcVendedor.getTOTLIQ().multiply(BigDecimal.valueOf(100)).divide(totalGeral01, RoundingMode.HALF_DOWN);
        this.prtcst = abcVendedor.getCSTPRT().multiply(BigDecimal.valueOf(100)).divide(totalGeral02, RoundingMode.HALF_DOWN);
    }
}
