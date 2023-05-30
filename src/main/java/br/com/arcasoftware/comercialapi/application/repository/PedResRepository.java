package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.Pedres;
import br.com.arcasoftware.comercialapi.model.IReportPedRe2Detail;
import br.com.arcasoftware.comercialapi.model.IReportPedResHead;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedResRepository extends CrudRepository<Pedres, Long> {

    Optional<Pedres> findByNumres(Long numres);

    @Query(nativeQuery = true, value = "select fat.arqnfe from FATPED fat where fat.numres = :numres")
    byte[] getNfePedido(Integer numres);

    @Query(nativeQuery = true, value = "" +
            "SELECT\n" +
            "   (select count(1) from pedre2\n" +
            "      WHERE P.NUMRES = pedre2.NUMRES\n" +
            "        AND P.DTERES = pedre2.DTERES\n" +
            "        AND P.CODEMP = pedre2.CODEMP) as QTD_ITENS,\n" +
            "P.CODEMP,\n" +
            "P.DTERES,\n" +
            "P.NUMRES,\n" +
            "P.HRERES,\n" +
            "P.DTFRES,\n" +
            "P.UFERES,\n" +
            "P.CGCCLI,\n" +
            "\n" +
            "P.QTDIMP,\n" +
            "P.NOMENT,\n" +
            "P.DTEFIN,\n" +
            "P.HREFIN,\n" +
            "\n" +
            "P.PEDANT,\n" +
            "P.QTIRES,\n" +
            "P.QTPRES,\n" +
            "P.LANEST,\n" +
            "P.CODFIL,\n" +
            "P.CODCLI,\n" +
            "P.CODTRA,\n" +
            "P.CODTIP,\n" +
            "P.CODVEN,\n" +
            "P.CODPFA,\n" +
            "P.TIPPFA,\n" +
            "P.CODATD,\n" +
            "P.TOTRES,\n" +
            "P.TOTICM,\n" +
            "P.TOTSUB,\n" +
            "P.TOTGER,\n" +
            "P.TOTCST,\n" +
            "P.MEDDSC,\n" +
            "P.TOTDSC,\n" +
            "P.MEDACR,\n" +
            "P.TOTACR,\n" +
            "P.MEDPRM,\n" +
            "P.TOTDSP,\n" +
            "P.MEDCOM,\n" +
            "P.TOTCOM,\n" +
            "P.MEDDCO,\n" +
            "P.TOTDCO,\n" +
            "P.DSCREG,\n" +
            "P.TOTDSR,\n" +
            "P.TOTIPI,\n" +
            "P.FATGER,\n" +
            "P.DEVGER,\n" +
            "P.SLDGER,\n" +
            "P.TOTREN,\n" +
            "P.USUREJ,\n" +
            "P.FLGOCO,\n" +
            "P.FLGIMP,\n" +
            "P.ATUEST,\n" +
            "P.SITRES,\n" +
            "P.CIDCLI,\n" +
            "P.UFECLI,\n" +
            "P.CEPCLI,\n" +
            "P.OBSRES,\n" +
            "P.TOTFRT,\n" +
            "P.TOTSEG,\n" +
            "P.TOTOUTDESP,\n" +
            "(P.TOTFRT + P.TOTSEG + P.TOTOUTDESP) TOTDESPESA,\n" +
            "P.ID_FRETE,\n" +
            "C.NOMCLI,\n" +
            "A.APEATD,\n" +
            "P.FLGFEC,\n" +
            "P.INSCLI,\n" +
            "T.NOMTRA,\n" +
            "T.CGCTRA,\n" +
            "T.CIDTRA,\n" +
            "T.SIGUFE UFETRA,\n" +
            "T.CEPTRA,\n" +
            "T.PRTTRA||'-'||T.FONTRA FONETRA,\n" +
            "F.DESCFRT,\n" +
            "V.emaven,\n" +
            "V.APEVEN,\n" +
            "V.PRFVEN||'-'||V.FONVEN TELEFONE,\n" +
            "CASE WHEN COALESCE(P.CODTRA, 0) > 0 THEN T.TENTRA ||' '|| T.ENDTRA ||', '|| T.NUMTRA || ' ' || T.BAITRA ELSE '' END ENDTRA,\n" +
            "P.TENCLI ||' '|| P.ENDCLI ||', '|| P.NUMCLI || ' ' || P.BAICLI ENDENT,\n" +
            "G.NOMEMP,\n" +
            "G.CGCEMP,\n" +
            "G.INSEMP,\n" +
            "G.TENEMP ||' '|| G.ENDEMP ||', '|| G.NUMEMP || ' ' || G.BAIEMP END1,\n" +
            "G.CIDEMP ||' '|| G.SIGUFE ||' '|| G.CEPEMP END2,\n" +
            "'('||G.PRTEMP||')'||G.FONEMP FONE,\n" +
            "G.EMAEMP,\n" +
            "G.WEBEMP\n" +
            "FROM PEDRES P\n" +
            "LEFT JOIN FINCLI C ON (P.CODCLI = C.CODCLI)\n" +
            "LEFT JOIN FINVEN V ON (P.CODVEN = V.CODVEN)\n" +
            "LEFT JOIN FINATD A ON (P.CODATD = A.CODATD)\n" +
            "LEFT JOIN GEREMP G ON (G.CODEMP = P.CODEMP)\n" +
            "LEFT JOIN FINTRA T ON (P.CODTRA = T.CODTRA)\n" +
            "LEFT JOIN TIPFRT F ON (F.ID = P.ID_FRETE)\n" +
            "WHERE P.NUMRES = :numres\n" +
            "\n" +
            "ORDER BY P.NUMRES")
    IReportPedResHead getReportPedRes(@Param("numres") Integer numres);

    @Query(nativeQuery = true, value = "" +
            "SELECT 'S' ATENDE,\n" +
            "PE2.CODEMP,\n" +
            "PE2.DTERES,\n" +
            "PE2.NUMRES,\n" +
            "PE2.SEQRE2,\n" +
            "PE2.CODGRU||'.'||PE2.CODSUB||'.'||PE2.CODPRO PRODUTO,\n" +
            "PE2.DESRE2,\n" +
            "PE2.QTPRE2,\n" +
            "PE2.VLQRE2,\n" +
            "PE2.ICMRE2,\n" +
            "PE2.TOTRE2,\n" +
            "PE2.TOTREN,\n" +
            "PE2.FLGVAL,\n" +
            "PE2.FLGPAC,\n" +
            "PE2.FLGLIB,\n" +
            "PE2.CLSIPI,\n" +
            "PE2.CODST1||PE2.CODST2 CST,\n" +
            "PE2.CODCFO,\n" +
            "PE2.CODUND,\n" +
            "PE2.VLURE2,\n" +
            "PE2.DSCRE2,\n" +
            "PE2.MRGSUB, \n" +
            "PE2.IPIRE2,\n" +
            "PE2.TOTICM, \n" +
            "PE2.TOTIPI,\n" +
            "PE2.TOTSUB,\n" +
            "PE2.DSRRE2,\n" +
            "PE2.TOTDSR,\n" +
            "PE2.OBSRE2,\n" +
            "PE2.TOTFRT,\n" +
            "PE2.TOTSEG,\n" +
            "PE2.TOTOUTDESP,\n" +
            "(PE2.TOTFRT + PE2.TOTSEG + PE2.TOTOUTDESP) TOTDESPESA,\n" +
            "PE2.NUMPEDCOMPRA,\n" +
            "PE2.NUMITEMCOMPRA,\n" +
            "PE2.TOTGE2,\n" +
            "PE2.TOTITETRB,\n" +
            "estMrc.NOMMRC,\n" +
            "estpro.QTDEMB,\n" +
            "estpro.REFPRO,\n" +
            "coalesce(estpro.LOCPRO,'') as LOCPRO,\n" +
            "PE2.NRORE2\n" +
            "\n" +
            "FROM PEDRE2 PE2\n" +
            "\n" +
            "join EstPro on EstPro.CodClp = PE2.CodClp\n" +
            "     and EstPro.CodGru = PE2.CodGru\n" +
            "     and EstPro.CodSub = PE2.CodSub\n" +
            "     and EstPro.CodPro = PE2.CodPro\n" +
            "left join estmrc on estmrc.codmrc = estpro.codmrc\n" +
            "     Where PE2.CodEmp = :codemp\n" +
            "     and PE2.DteRes = :dteres\n" +
            "     and PE2.NumRes = :numres\n" +
            "--ORDER BY 42 ASC")
    List<IReportPedRe2Detail> getReportPedRe2(@Param("codemp") Integer codemp, @Param("dteres") Date dteres, @Param("numres") Integer numres);

}
