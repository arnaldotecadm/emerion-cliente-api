package br.com.arcasoftware.comercialapi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public interface IReportPedResHead {

    Integer getqtdItens();

    Integer getCODEMP();

    Date getDTERES();

    Integer getNUMRES();

    String getHRERES();

    Date getDTFRES();

    String getUFERES();

    String getCGCCLI();

    String getQTDIMP();

    String getNOMENT();

    Timestamp getDTEFIN();

    String getHREFIN();

    String getPEDANT();

    BigDecimal getQTIRES();

    BigDecimal getQTPRES();

    String getLANEST();

    Integer getCODFIL();

    Integer getCODCLI();

    Integer getCODTRA();

    Integer getCODTIP();

    Integer getCODVEN();

    String getCODPFA();

    String getTIPPFA();

    Integer getCODATD();

    BigDecimal getTOTRES();

    BigDecimal getTOTICM();

    BigDecimal getTOTSUB();

    BigDecimal getTOTGER();

    BigDecimal getTOTCST();

    BigDecimal getMEDDSC();

    BigDecimal getTOTDSC();

    BigDecimal getMEDACR();

    BigDecimal getTOTACR();

    BigDecimal getMEDPRM();

    BigDecimal getTOTDSP();

    BigDecimal getMEDCOM();

    BigDecimal getTOTCOM();

    BigDecimal getMEDDCO();

    BigDecimal getTOTDCO();

    BigDecimal getDSCREG();

    BigDecimal getTOTDSR();

    BigDecimal getTOTIPI();

    BigDecimal getFATGER();

    BigDecimal getDEVGER();

    BigDecimal getSLDGER();

    BigDecimal getTOTREN();

    Integer getUSUREJ();

    String getFLGOCO();

    String getFLGIMP();

    String getATUEST();

    String getSITRES();

    String getCIDCLI();

    String getUFECLI();

    String getCEPCLI();

    String getOBSRES();

    BigDecimal getTOTFRT();

    BigDecimal getTOTSEG();

    BigDecimal getTOTOUTDESP();

    BigDecimal getTOTDESPESA();

    Integer getidFrete();

    String getNOMCLI();

    String getAPEVEN();

    String getAPEATD();

    String getFLGFEC();

    String getINSCLI();

    String getNOMEMP();

    String getCGCEMP();

    String getINSEMP();

    String getNOMTRA();

    String getCGCTRA();

    String getCIDTRA();

    String getUFETRA();

    String getCEPTRA();

    String getFONETRA();

    String getEMAVEN();

    String getDESCFRT();

    String getTELEFONE();

    String getENDTRA();

    String getENDENT();

    String getEND1();

    String getEND2();

    String getFONE();

    String getEMAEMP();

    String getWEBEMP();
}
