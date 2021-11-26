package br.com.arcasoftware.comercialapi.model;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProcessamentoDadosSaida {
	@Include
	String codClp;
	@Include
	String codGru;
	@Include
	String codSub;
	@Include
	String codPro;

	String proNcm;
	String codUnd;
	String origem;
	String cbaPro;
	String descPro;
	BigDecimal qtdEmb;

	String icmsRegra;
	String icmsTipo;
	String cstIcms;
	BigDecimal icmsAliq;
	BigDecimal icmsBase;
	BigDecimal icmsRed;
	BigDecimal icmsIncRev;
	BigDecimal icmsIncFin;
	String cfop;

	String strRegra;
	String strTipo;
	BigDecimal strAliq;
	BigDecimal strMva;
	BigDecimal strBase;
	BigDecimal strRed;

	String ipiRegra;
	String ipiTipo;
	String ipiCst;
	BigDecimal ipiAliq;
	BigDecimal ipiBase;
	BigDecimal ipiPerImp;

	BigDecimal pisAliq;
	String pisCst;
	String pisFlgDescZf;

	BigDecimal cofAliq;
	String cofCst;
	String cofFlgDescZf;

	BigDecimal pfaFrtIcms;
	BigDecimal pfaSegIcms;
	BigDecimal pfaOutIcms;
	BigDecimal pfaDscIcms;

	BigDecimal pfaFrtSub;
	BigDecimal pfaSegSub;
	BigDecimal pfaOutSub;
	BigDecimal pfaDscSub;

	BigDecimal pfaFrtIpi;
	BigDecimal pfaSegIpi;
	BigDecimal pfaOutIpi;
	BigDecimal pfaDscIpi;

}