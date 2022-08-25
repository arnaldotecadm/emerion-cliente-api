package br.com.arcasoftware.comercialapi.model;

import java.math.BigDecimal;
import java.util.Date;

public interface IPedResDTO {

	Integer getCodcli();

	Integer getNUMRES();

	String getCODPFA();

	BigDecimal getTOTGER();

	BigDecimal getTOTRES();

	BigDecimal getTOTREN();

	BigDecimal getFATGER();

	BigDecimal getDEVGER();

	BigDecimal getSLDGER();

	String getDTERES();

	String getDTFRES();

	String getUFERES();

	String getFLGIMP();

	String getSITRES();

	String getOBSRES();

	String getOBSPRO();

	Integer getQTDIMP();

	Integer getqtdItens();

	String getNOMVEN();

	String getNONATD();

	String getNOMCLI();

	String getPossuiNFe();
}
