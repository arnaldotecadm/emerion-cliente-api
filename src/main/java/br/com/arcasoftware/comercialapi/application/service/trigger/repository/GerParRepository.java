package br.com.arcasoftware.comercialapi.application.service.trigger.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.arcasoftwares.model.Estpro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.arcasoftware.comercialapi.application.service.trigger.model.EstIteInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.FlgConInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.QtdPedRe2Interface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.RegrasPadraoFatInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.RegrasProdutoInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.SitPedInterface;

public interface GerParRepository extends CrudRepository<Estpro, Integer>{

	@Query(nativeQuery = true, value = "SELECT flgCon FROM GERPAR")
    FlgConInterface getFlgCon();

	@Query(nativeQuery = true, value = "SELECT sitPed FROM LOJPED WHERE NUMPED = :numPed")
    SitPedInterface getSitLojPed(@Param("numPed") Integer numped);

	@Query(nativeQuery = true, value = "SELECT sitres as sitPed FROM PEDRES WHERE NUMRES = :numPed")
	SitPedInterface getSitPedRes(@Param("numPed") Integer numped);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) as totalRegistros FROM PEDRE2 "
			+ " WHERE CODEMP = :codemp AND DTERES = :dteres AND NUMRES = :numres AND "
			+ " CODCLP = :codclp AND CODGRU = :codgru AND CODSUB = :codsub AND CODPRO = :codpro ")
    Integer getTotalItens(@Param("codemp") Integer codemp, @Param("dteres") Date dteres,
                          @Param("numres") Integer numres, @Param("codclp") String codclp, @Param("codgru") String codgru,
                          @Param("codsub") String codsub, @Param("codpro") String codpro);

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM PEDRE2"
			+ " WHERE CODEMP = :codemp AND DTERES = :dteres AND NUMRES = :numres AND ISSRE2 > 0")
    Integer getTotalItensISSR2(@Param("codemp") Integer codemp, @Param("dteres") Date dteres,
                               @Param("numres") Integer numres);

	@Query(nativeQuery = true, value = "SELECT codBar " + " FROM ESTBAR "
			+ " WHERE CODCLP = :codclp AND CODGRU = :codgru AND CODSUB = :codsub AND CODPRO = :codpro AND "
			+ " FLGINT = 'Sim' ORDER BY 1")
	List<String> getCodBarByProduto(@Param("codclp") String codclp, @Param("codgru") String codgru,
							@Param("codsub") String codsub, @Param("codpro") String codpro);

	@Query(nativeQuery = true, value = "SELECT TIPPED FROM PEDPAR")
    String getTipPedFromParametro();

	@Query(nativeQuery = true, value = "SELECT coalesce(ACRMAX,0) FROM PEDPAR")
    BigDecimal getAcrMaxFromParametro();

	@Query(nativeQuery = true, value = "SELECT QTIRES,SEQITE,CODTIP,CONSUM,CODFIL,LANEST,CODIPI,TIPIPI,TRBIPI,REDIPI,BSCIPI,CODICM,"
			+ "TIPICM,TRBICM,REDICM,BSCICM,INCREV,INCFIN,DSCREG,FLGCTB,UFERES,DSCCOM,FLGTAB,FLGRES,"
			+ "EMPCTA,PRCCTA,MODPFA,MESRES,ANORES,CODTCL,TIPCOM,CODCOM,TIPCPA,CODATD,FLGIPI,CODCFO,"
			+ "TRBPIS,TRBCOF,ALIQPIS,ALIQCOF,CSTPIS,CSTCOF,FRTICM,SEGICM,DESICM,"
			+ "FRTIPI,SEGIPI,DESIPI,COMICLI,CODCLI,SUFR_ICMS,SUFR_IPI,SUFR_PIS,SUFR_COFINS,regtrbemp,"
			+ "FRTSUB,SEGSUB,DESSUB,DSCICM,DSCIPI,DSCSUB" + " FROM PEDRES" + " WHERE CODEMP = :codemp"
			+ "AND DTERES = :dteres" + " AND NUMRES = :numres")
    RegrasPadraoFatInterface getRegrasPadraoFatByPedido(@Param("codemp") Integer codemp,
                                                        @Param("dteres") Date dteres, @Param("numres") Integer numres);

	@Query(nativeQuery = true, value = " SELECT coalesce(MAX(SEQRE2),0) +1 as sequence " + " FROM PEDRE2"
			+ " WHERE CODEMP = :codemp" + " AND DTERES = :dteres" + " AND NUMRES = :numres")
    Integer getMaxSeqRe2(@Param("codemp") Integer codemp, @Param("dteres") Date dteres,
                         @Param("numres") Integer numres);

	@Query(nativeQuery = true, value = " SELECT IPISAI,IPITSD,ICMSAI,ICMTSD,DSCPRO,CODUNS,PESLIQ,PESBRT,FLBPRO,"
			+ " CODSTS,TIPSTS,QTDEMB,ESTPRO.REFPRO,CODCOM,CODCAT,CODTIP,CODMRC,ISSPRO,QTDVOL,CODST1, CODANP"
			+ " FROM ESTPRO"
			+ " WHERE CODCLP = :codclp AND CODGRU = :codgru AND CODSUB = :codsub AND CODPRO = :codpro")
    RegrasProdutoInterface getRegrasProdutoByIdentificacao(@Param("codclp") String codclp,
                                                           @Param("codgru") String codgru, @Param("codsub") String codsub, @Param("codpro") String codpro);

	@Query(nativeQuery = true, value = " SELECT VCHITE,VREITE,VCPITE,VPRITE,CSTITE,VCRITE,"
			+ "VMEITE,VPFITE,VB1ITE,VB2ITE,VB3ITE,VB4ITE,"
			+ "VB5ITE,DS1ITE,DS2ITE,DS3ITE,DS4ITE,DS5ITE,"
			+ "VPFOUT,CODCM1,CODCM2,CODCM3,CODCM4,CODCM5,"
			+ "MK1ITE,MK2ITE,MK3ITE,MK4ITE,MK5ITE" + " FROM ESTITE" + " WHERE CODEMP = :codemp"
			+ "AND CODCLP = :codclp AND CODGRU = :codgru AND CODSUB = :codsub AND CODPRO = :codpro")
    EstIteInterface getestIteByIdentificacao(@Param("codemp") Integer codemp, @Param("codclp") String codclp,
                                             @Param("codgru") String codgru, @Param("codsub") String codsub, @Param("codpro") String codpro);

	@Query(nativeQuery = true, value = "SELECT PERCOM FROM FINCOM WHERE CODCOM = :codcom")
    BigDecimal getPerCom(@Param("codcom") String codcom);

	@Query(nativeQuery = true, value = "SELECT tipemp FROM geremp WHERE codemp = :codemp")
    String getTipEmp(@Param("codemp") Integer codemp);

	@Query(nativeQuery = true, value = "SELECT CODPRM FROM PEDPRM WHERE :dteres BETWEEN DTIPRM AND DTFPRM ")
    Integer getCodPrm(@Param("dteres") Date dteres);

	@Query(nativeQuery = true, value = "SELECT SEQPR2,DS1PR2,QT1PR2,DS2PR2,QT2PR2,DS3PR2,"
			+ " QT3PR2,DS4PR2,QT4PR2,DS5PR2,QT5PR2"
			+ " FROM PEDPR2"
			+ " WHERE CODPRM = :codprm"
			+ " AND CODCLP = :codclp AND CODGRU = :codgru AND CODSUB = :codsub AND CODPRO = :codpro")
    QtdPedRe2Interface getQtdPedRe2ByIdentificacaoItem(@Param("codprm") Integer codprm,
                                                       @Param("codclp") String codclp, @Param("codgru") String codgru, @Param("codsub") String codsub,
                                                       @Param("codpro") String codpro);

	@Query(nativeQuery = true, value = "SELECT ROUND(SUM(QTPRE2),4) FROM PEDRE2 WHERE CODPRM = :codprm AND SEQPR2 = :seqpr2 AND TABPRC = :tabprc")
    BigDecimal getQtdPed(@Param("codprm") Integer codprm, @Param("seqpr2") Integer seqpr2,
                         @Param("tabprc") Integer tabprc);
}
