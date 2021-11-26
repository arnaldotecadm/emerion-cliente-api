package br.com.arcasoftware.comercialapi.application.service.trigger.pedre2;

import br.com.arcasoftware.comercialapi.application.service.trigger.model.EstIteInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.RegrasPadraoFatInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.RegrasProdutoInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.service.TriggerService;
import br.com.arcasoftwares.model.Pedre2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
public class PedRe2BefUpd00 {

    private final BigDecimal zero = new BigDecimal(0);
    private final BigDecimal cem = BigDecimal.valueOf(100);

    private final TriggerService service;
    private final PedRe2CommonMath commonMath;

    @Autowired
    public PedRe2BefUpd00(TriggerService service, PedRe2CommonMath commonMath) {
        this.service = service;
        this.commonMath = commonMath;
    }

    public Pedre2 processar(Pedre2 pedre2) throws ValidationException {
        Integer codemp = pedre2.getId().getCodemp();
        Date dteres = pedre2.getId().getDteres();
        Integer numres = pedre2.getId().getNumres();

        String flgCon;
        String conTab = "";
        String codCm1 = "";
        String codCm2 = "";
        String codCm3 = "";
        String codCm4 = "";
        String codCm5 = "";

        int mkpAbaixo = 0;/* 0=False;1=True */

        flgCon = this.service.getFlgCon().getFlgCon();

        if (!flgCon.equalsIgnoreCase("*")) {

            if (!pedre2.getFlaseq().equalsIgnoreCase("N")) {

                if (!pedre2.getFlaseq().equalsIgnoreCase("F") &&
                        !pedre2.getFlaseq().equalsIgnoreCase("*")) {
                    CodCMTabs codCMTabs = new CodCMTabs(codCm1, codCm2, codCm3, codCm4, codCm5);
                    processarRegras(pedre2, codemp, dteres, numres, conTab, codCMTabs, mkpAbaixo);
                }

            } else
                throw new ValidationException("EXCEPTION_ERR999");

        }

        return pedre2;
    }

    private void processarRegras(Pedre2 pedre2, Integer codemp, Date dteres, Integer numres, String conTab, CodCMTabs codcmTabs, int mkpAbaixo) throws ValidationException {
        BigDecimal vluRe2;
        if (pedre2.getDesre2().length() > 0) {

            validadeInputs(pedre2);

            RegrasPadraoFatInterface regraPfa = this.service.getRegrasPadraoFatByPedido(codemp,
                    dteres, numres);

            if (regraPfa.getCODTCL() != null && regraPfa.getCODTCL() == 3) {
                pedre2.setDscorgpublic(pedre2.getIcmre2());
            }

            RegrasProdutoInterface regraProd = this.service.getRegrasProdutoByIdentificacao(
                    pedre2.getCodclp(), pedre2.getCodgru(), pedre2.getCodsub(), pedre2.getCodpro());

            pedre2.setVolre2(regraProd.getQTDVOL());
            pedre2.setIssre2(regraProd.getISSPRO());
            /* Carrega os dados da ESTITE */
            EstIteInterface estiteDados = this.service.getestIteByIdentificacao(codemp,
                    pedre2.getCodclp(), pedre2.getCodgru(), pedre2.getCodsub(), pedre2.getCodpro());

            TipEmpEnum tipEmp = TipEmpEnum.getEnumFromDescription(this.service.getTipEmp(codemp));

            validateCompanyType(pedre2, tipEmp);

            if (!pedre2.getFlgfec()) {

                if (!regraPfa.getMODPFA().equals(ModPfaEnum.TRANSFERENCIAS.getLabel())) {

                    switch (pedre2.getTabprc()) {
                        case 1: {
                            vluRe2 = estiteDados.getVB1ITE();
                            conTab = codcmTabs.getCodCm1();
                            break;
                        }
                        case 2: {
                            vluRe2 = estiteDados.getVB2ITE();
                            conTab = codcmTabs.getCodCm2();
                            break;
                        }
                        case 3: {
                            vluRe2 = estiteDados.getVB3ITE();
                            conTab = codcmTabs.getCodCm3();
                            break;
                        }
                        case 4: {
                            vluRe2 = estiteDados.getVB4ITE();
                            conTab = codcmTabs.getCodCm4();
                            break;
                        }
                        case 5: {
                            vluRe2 = estiteDados.getVB5ITE();
                            conTab = codcmTabs.getCodCm5();
                            break;
                        }
                        default: {
                            vluRe2 = estiteDados.getVB1ITE();
                            conTab = codcmTabs.getCodCm1();
                        }
                    }

                } else
                    vluRe2 = pedre2.getVlure2();

            } else {
                vluRe2 = pedre2.getVlure2();
            }

            pedre2.setVlure2(vluRe2);

            if (pedre2.getVlure2() != null) {

                if (pedre2.getVlure2().compareTo(zero) <= 0) {
                    throw new ValidationException("EXCEPTION_ERR267");
                }

            } else {
                throw new ValidationException("EXCEPTION_ERR267");
            }

            pedre2.setLinre2(1);
            pedre2.setBascom(zero);
            pedre2.setTotcom(zero);
            pedre2.setBascat(zero);
            pedre2.setTotcat(zero);
            pedre2.setDscper(zero);
            pedre2.setTotven(zero);
            pedre2.setTotcst(zero);
            pedre2.setBasipi(zero);
            pedre2.setTotipi(zero);
            pedre2.setTotipc(zero);
            pedre2.setBasicm(zero);
            pedre2.setToticm(zero);
            pedre2.setBassub(zero);
            pedre2.setTotsub(zero);
            pedre2.setTotre2(zero);
            pedre2.setTotren(zero);
            pedre2.setTotge2(zero);
            pedre2.setTotliq(zero);
            pedre2.setTotbrt(zero);
            pedre2.setTotvol(0);
            pedre2.setVdrre2(zero);
            pedre2.setVlqre2(zero);
            pedre2.setTotdsc(zero);
            pedre2.setTotdsr(zero);
            pedre2.setTotdsp(zero);
            pedre2.setTotacr(zero);
            pedre2.setTotdco(zero);
            pedre2.setBasiss(zero);
            pedre2.setTotiss(zero);
            pedre2.setTotper(zero);
            pedre2.setDifdsc(zero);
            pedre2.setMarpre(zero);
            pedre2.setMarped(zero);
            pedre2.setLucrol(zero);
            pedre2.setLucrop(zero);
            pedre2.setFlaseq(" ");
            pedre2.setCodprm(null);
            pedre2.setSeqpr2(null);
            pedre2.setFlgval("Nao");
            pedre2.setFlgpac("Nao");
            pedre2.setFlglib("Nao");
            pedre2.setFlgres(regraPfa.getFLGRES());
            pedre2.setSldre2(
                    pedre2.getQtpre2().subtract(pedre2.getQtfre2()));
            pedre2.setSldfab(pedre2.getQtpre2().subtract(pedre2.getQtdfab()));

            pedre2.setVlqre2(pedre2.getVlure2());

            commonMath.carregaDescontosPermitidos(pedre2, estiteDados.getDS1ITE(),
                    estiteDados.getDS2ITE(), estiteDados.getDS3ITE(), estiteDados.getDS4ITE(),
                    estiteDados.getDS5ITE());

            commonMath.processaQtdPorEmbalagem(pedre2, ModPfaEnum.getEnumFromDescription(regraPfa.getMODPFA().trim()),
                    regraProd.getQTDEMB());

            commonMath.aplicaDescontoPromocional(pedre2);

            commonMath.aplicaAcrescimoVendedor(pedre2);

            commonMath.aplicaDesconto(pedre2);

            commonMath.aplicaDescontoVendedor(pedre2);

            if (pedre2.getDsrre2().compareTo(zero) > 0) {

                pedre2.setVdrre2(
                        pedre2.getDsrre2().multiply(pedre2.getVlqre2()).divide(cem, RoundingMode.HALF_DOWN));

                pedre2.setVlqre2(
                        pedre2.getVlqre2().subtract(pedre2.getVdrre2()));

            }

            if ((regraPfa.getCODTCL() == 3) && (pedre2.getDscorgpublic().compareTo(zero) > 0) && new ArrayList<>(Arrays.asList("00", "90", "20", "10", "40"))
                    .contains(pedre2.getCodst2())) {

                pedre2.setVldorgpublic(pedre2.getVlqre2().multiply(
                        pedre2.getDscorgpublic().divide(cem, RoundingMode.HALF_DOWN)));

                pedre2.setVlqre2(pedre2.getVlqre2().subtract(pedre2.getVldorgpublic()));
                if (tipEmp != TipEmpEnum.SIMPLES_NACIONAL) {
                    pedre2.setCodst2("40");
                } else {
                    pedre2.setCodst2("400");
                }
            }

            if (!pedre2.getFlgfec()) {

                if (pedre2.getFlgreq() && pedre2.getDscre2().compareTo(pedre2.getDscper()) > 0) {
                    pedre2.setFlgval("Sim");
                }

                if (pedre2.getFlgreq()) {
                    BigDecimal maxAcr = this.service.getAcrMaxFromParametro();
                    if (maxAcr.compareTo(zero) > 0 && pedre2.getPacre2().compareTo(maxAcr) > 0) {
                        pedre2.setFlgpac("Sim");
                    }
                }

            }

            pedre2.setTotre2(pedre2.getVlqre2().multiply(pedre2.getQtpre2()));
            pedre2.setTotven(pedre2.getVlqre2().multiply(pedre2.getQtpre2()));
            pedre2.setTotcst(pedre2.getVcsre2().multiply(pedre2.getQtpre2()));
            pedre2.setTotliq(pedre2.getLiqre2().multiply(pedre2.getQtpre2()));

            pedre2.setTotvol(pedre2.getVolre2().multiply(pedre2.getQtpre2()).intValue());
            pedre2.setTotbrt(pedre2.getBrtre2().multiply(pedre2.getQtpre2()));

            if (!StringUtils.isBlank(pedre2.getObsre2())) {
                pedre2.setLinre2(pedre2.getLinre2() + 1);
            }
            if (!StringUtils.isBlank(pedre2.getRefre2())) {
                pedre2.setLinre2(pedre2.getLinre2() + 1);
            }

            pedre2.setTotge2(pedre2.getTotre2().subtract(pedre2.getTotdsr()));

            if (pedre2.getIssre2().compareTo(zero) > 0) {

                pedre2.setBasiss(pedre2.getTotge2());
                pedre2.setTotiss(pedre2.getIssre2().multiply(pedre2.getBasiss()).divide(cem, RoundingMode.HALF_DOWN));

            }

            processCSTIPI(pedre2, regraPfa);



            calculoPis(pedre2, regraPfa);
            calculoCofins(pedre2, regraPfa);

            if (null != regraPfa.getSUFRICMS() && regraPfa.getSUFRICMS().equalsIgnoreCase("S") &&
                    pedre2.getDscreg().compareTo(zero) > 0) {
                pedre2.setTotdsr(pedre2.getTotdsr()
                        .add(pedre2.getVlqre2().multiply(pedre2.getQtpre2()))
                        .multiply(pedre2.getDscreg()).divide(cem, RoundingMode.HALF_DOWN));
            }

            commonMath.processaDescZfPis(pedre2, regraPfa.getSUFRPIS(), tipEmp);

            commonMath.processaDescZfCof(pedre2, regraPfa.getSUFRCOFINS(), tipEmp);

            if (new ArrayList<>(Arrays.asList("10", "20", "70", "90", "900"))
                    .contains(pedre2.getCodst2()) && pedre2.getBasesb().compareTo(zero) == 0)
                pedre2.setBasesb(new BigDecimal(100));

            /* Calcula ICMS */
            if (pedre2.getBscicm().compareTo(zero) > 0) {
                commonMath.calculaICMS(pedre2, regraPfa.getCONSUM());

                if (pedre2.getBasesb().compareTo(zero) > 0) {
                    commonMath.calculaICMSST(pedre2, regraPfa.getCODTCL(),
                            regraPfa.getUFERES(), regraProd.getCODANP());
                }
            }

            /* NF-e Simples */

            if ((new ArrayList<>(Arrays.asList(1, 5)).contains(regraPfa.getCODTCL()))) {
                pedre2.setBassub(zero);
                pedre2.setTotsub(zero);
                pedre2.setMrgsub(zero);
                pedre2.setIcmsub(zero);
                pedre2.setBasesb(zero);
            }

            String tipPedFromParametro = this.service.getTipPedFromParametro();
            commonMath.processoNF20(pedre2, tipPedFromParametro, regraPfa.getSUFRICMS(),
                    regraPfa.getFLGIPI());

            /* Carrega os dados da ESTITE */
            estiteDados = this.service.getestIteByIdentificacao(codemp, pedre2.getCodclp(),
                    pedre2.getCodgru(), pedre2.getCodsub(), pedre2.getCodpro());

            if (TipComEnum.getEnumFromDescription(regraPfa.getTIPCOM().trim()) == TipComEnum.VALOR_MARKUP) {
                conTab = commonMath.processaValorMarkup(pedre2, conTab, codcmTabs.getCodCm1(), codcmTabs.getCodCm2(),
                        codcmTabs.getCodCm3(), codcmTabs.getCodCm4(), codcmTabs.getCodCm5(), estiteDados.getMK1(), estiteDados.getMK2(),
                        estiteDados.getMK3(), estiteDados.getMK4(), estiteDados.getMK5());


                if (pedre2.getMarped().compareTo(estiteDados.getMK1()) < 0) {
                    mkpAbaixo = 1;
                }
            }

            /* Verifica a Comissao */
            /* Caso nao for Fechamento de Vale */
            if (!pedre2.getFlgfec() && (TipComEnum.getEnumFromDescription(regraPfa.getTIPCOM().trim()) == TipComEnum.TABELA_PRECO)
                    || (TipComEnum.getEnumFromDescription(regraPfa.getTIPCOM().trim()) == TipComEnum.VALOR_MARKUP)) {

                pedre2.setCodcom(conTab);
                if (pedre2.getCodcom().isEmpty()) {
                    throw new ValidationException("Comissao por tabela nao informado..");
                }
            }

            if (!pedre2.getFlgfec()) {

                BigDecimal percom = this.service.getPerCom(pedre2.getCodcom());
                percom = null != percom ? percom : zero;

                if (percom.compareTo(zero) > 0) {
                    pedre2.setPcore2(percom);
                }

                if (TipComEnum.getEnumFromDescription(regraPfa.getTIPCOM().trim()) == TipComEnum.VALOR_MARKUP && mkpAbaixo == 1) {
                    pedre2.setPcore2(
                            pedre2.getDscre2().subtract(cem).divide(cem.multiply(percom), RoundingMode.HALF_DOWN));
                }
            }

            if (pedre2.getPcore2().compareTo(zero) > 0) {
                pedre2.setTotcom((pedre2.getPcore2().multiply(pedre2.getBascom()).divide(cem, RoundingMode.HALF_DOWN)));
            }

            if (regraPfa.getFLGIPI().equalsIgnoreCase("Sim")) {
                pedre2.setBascat(pedre2.getTotre2().subtract(pedre2.getTotdsr()));
            } else {
                pedre2.setBascat(pedre2.getTotre2().subtract(pedre2.getTotdsr())
                        .subtract(pedre2.getTotipc()));
            }
            if (pedre2.getPcoatd().compareTo(zero) > 0) {
                pedre2.setTotcat(pedre2.getPcoatd().multiply(pedre2.getBascat()).divide(cem, RoundingMode.HALF_DOWN));
            }

            pedre2.setTotgeral(pedre2.getTotre2()
                    .subtract(pedre2.getTotdescinc().add(pedre2.getTotdsr()))
                    .add(pedre2.getTotfrt()).add(pedre2.getTotseg())
                    .add(pedre2.getTotoutdesp()).add(pedre2.getTotipi())
                    .add(pedre2.getTotsub()));

            pedre2.setTotitetrb(pedre2.getTotre2()
                    .add(pedre2.getTotfrt()).add(pedre2.getTotseg())
                    .add(pedre2.getTotoutdesp()).add(pedre2.getTotipi())
                    .add(pedre2.getTotsub()));

            pedre2.setTotibpt(pedre2.getTotgeral()
                    .multiply(pedre2.getAliqIbpt().divide(cem, RoundingMode.HALF_DOWN)));

            // --Calculo do Markup
            if (pedre2.getTotcst().compareTo(zero) > 0) {
                pedre2.setTotren(pedre2.getTotre2().subtract(pedre2.getTotcst()).multiply(cem).divide(pedre2.getTotcst(), RoundingMode.HALF_DOWN));
            }

        } else
            throw new ValidationException("EXCEPTION_ERR258");
    }

    private void validateCompanyType(Pedre2 pedre2, TipEmpEnum tipEmp) throws ValidationException {
        if (tipEmp == TipEmpEnum.SIMPLES_NACIONAL) {
            if (!new ArrayList<>(Arrays.asList("101", "102", "103", "201", "202", "203",
                    "300", "400", "500", "900")).contains(pedre2.getCodst2())) {
                throw new ValidationException(
                        "Situacao tributaria de incorreta para empresa Simples Nascional");
            }

        } else {
            if (!new ArrayList<>(Arrays.asList("00", "10", "20", "30", "40", "41", "50",
                    "51", "60", "70", "90")).contains(pedre2.getCodst2().trim())) {
                throw new ValidationException(
                        "Situacao tributaria de incorreta para empresa Regime Normal");
            }
        }
    }

    private void calculoPis(Pedre2 pedre2, RegrasPadraoFatInterface regraPfa) {
        if (new ArrayList<>(Arrays.asList("07", "08", "09"))
                .contains(pedre2.getCstpis())) {
            pedre2.setAliqpis(zero);
            pedre2.setTotpis(zero);
            pedre2.setBaspis(zero);
        }

        if (pedre2.getCstpis().equalsIgnoreCase("06")) {
            pedre2.setAliqpis(zero);
            pedre2.setTotpis(zero);

        }

        // --Calculo Pis
        if (null != regraPfa.getSUFRPIS() && regraPfa.getSUFRPIS().equalsIgnoreCase("S")) {
            pedre2.setBaspis(zero);
            pedre2.setTotpis(zero);
            pedre2.setCstpis("09");
        } else {
            pedre2.setBaspis(pedre2.getTotre2());
            if (pedre2.getAliqpis().compareTo(zero) > 0) {
                pedre2.setTotpis(pedre2.getBaspis()
                        .subtract(pedre2.getBaspis().subtract((pedre2.getBaspis()
                                .multiply(pedre2.getAliqpis())).divide(cem, RoundingMode.HALF_DOWN))));
            } else {
                pedre2.setTotpis(zero);
            }
        }
    }

    private void calculoCofins(Pedre2 pedre2, RegrasPadraoFatInterface regraPfa) {
        // --Calculo Cofins
        if (null != regraPfa.getSUFRCOFINS() && regraPfa.getSUFRCOFINS().equalsIgnoreCase("S")) {
            pedre2.setBascof(zero);
            pedre2.setTotcof(zero);
            pedre2.setCstcof("09");
        } else {
            pedre2.setBascof(pedre2.getTotre2());
            if (pedre2.getAliqcof().compareTo(zero) > 0) {
                pedre2.setTotcof(pedre2.getBascof()
                        .subtract(pedre2.getBascof().subtract((pedre2.getBascof()
                                .multiply(pedre2.getAliqcof())).divide(cem, RoundingMode.HALF_DOWN))));
            } else {
                pedre2.setTotcof(zero);
            }
        }

        if (new ArrayList<>(Arrays.asList("07", "08", "09"))
                .contains(pedre2.getCstcof())) {
            pedre2.setAliqcof(zero);
            pedre2.setTotcof(zero);
            pedre2.setBascof(zero);
        }

        if (pedre2.getCstcof().equalsIgnoreCase("06")) {
            pedre2.setAliqcof(zero);
            pedre2.setTotcof(zero);
        }
    }

    private void processCSTIPI(Pedre2 pedre2, RegrasPadraoFatInterface regraPfa) {
        if (new ArrayList<>(Arrays.asList("50", "51", "99"))
                .contains(pedre2.getCstipi()) && !regraPfa.getSUFRIPI().equalsIgnoreCase("S")) {

            if (pedre2.getBscipi().compareTo(zero) > 0) {
                pedre2.setBasipi(pedre2.getTotre2().multiply(
                        pedre2.getBscipi().divide(cem, RoundingMode.HALF_DOWN)));

                pedre2.setBasipi(pedre2.getBasipi().add(pedre2
                        .getTotfrt()
                        .subtract(pedre2.getTotfrt()
                                .subtract(pedre2.getTotfrt().multiply(pedre2.getFrtipi()))
                                .divide(cem, RoundingMode.HALF_DOWN))));

                pedre2.setBasipi(pedre2.getBasipi().add(pedre2
                        .getTotoutdesp()
                        .subtract(pedre2.getTotoutdesp()
                                .subtract(pedre2.getTotoutdesp().multiply(pedre2.getSegipi()))
                                .divide(cem, RoundingMode.HALF_DOWN))));

                pedre2.setBasipi(pedre2.getBasipi().add(pedre2
                        .getTotseg()
                        .subtract(pedre2.getTotseg()
                                .subtract(pedre2.getTotseg()
                                        .multiply(pedre2.getDesipi()).divide(cem, RoundingMode.HALF_DOWN)))));

                pedre2.setBasipi(pedre2.getBasipi().subtract(pedre2
                        .getTotdescinc()
                        .subtract(pedre2.getTotdescinc()
                                .subtract(pedre2.getTotdescinc()
                                        .multiply(pedre2.getDscipi()).divide(cem, RoundingMode.HALF_DOWN)))));

                if (regraPfa.getFLGIPI().equalsIgnoreCase("Nao")) {
                    pedre2.setTotipi(pedre2.getBasipi().subtract(pedre2.getBasipi().divide(pedre2.getIpire2().divide(cem, RoundingMode.HALF_DOWN).add(BigDecimal.ONE), RoundingMode.HALF_DOWN)));
                } else {
                    pedre2.setTotipi(pedre2.getBasipi().multiply(pedre2.getIpire2()).divide(cem, RoundingMode.HALF_DOWN));

                }

                pedre2.setTotipc(pedre2.getTotipi());

            } else {
                pedre2.setCstipi("53");
                pedre2.setTotipi(zero);
                pedre2.setBasipi(zero);
                pedre2.setIpire2(zero);
            }
        }
    }

    private void validadeInputs(Pedre2 pedre2) throws ValidationException {
        if (pedre2.getQtpre2().compareTo(zero) <= 0)
            throw new ValidationException("EXCEPTION_ERR224");
        if (pedre2.getVlure2().compareTo(zero) <= 0)
            throw new ValidationException("EXCEPTION_ERR223");
        if (pedre2.getIcmre2() == null)
            throw new ValidationException("EXCEPTION_ERR257");
        if (pedre2.getIpire2() == null)
            throw new ValidationException("EXCEPTION_ERR256");
        if (pedre2.getTabprc() == null)
            throw new ValidationException("EXCEPTION_ERR247");
    }

}

@AllArgsConstructor
@Getter
class CodCMTabs {
    private String codCm1;
    private String codCm2;
    private String codCm3;
    private String codCm4;
    private String codCm5;
}