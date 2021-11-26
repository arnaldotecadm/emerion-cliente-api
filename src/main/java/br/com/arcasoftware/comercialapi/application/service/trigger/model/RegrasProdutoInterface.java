package br.com.arcasoftware.comercialapi.application.service.trigger.model;

import java.math.BigDecimal;

public interface RegrasProdutoInterface {
	BigDecimal getREGIPI();

	BigDecimal getTIPIPI();

	BigDecimal getREGPRO();

	BigDecimal getTIPPRO();

	String getDSCPRO();

	String getCODUND();

	BigDecimal getPESLIQ();

	BigDecimal getPESBRT();

	String getFLBPRO();

	BigDecimal getCODSTR();

	BigDecimal getTIPSTR();

	BigDecimal getQTDEMB();

	String getREFPRO();

	String getCODCOM();

	Integer getCODCAT();

	Integer getCODTIP();

	Integer getCODMRC();

	BigDecimal getISSPRO();

	BigDecimal getQTDVOL();

	BigDecimal getCODST1();
	
	String getCODANP();

}
