package br.com.arcasoftware.comercialapi.application.service.trigger.pedre2;

import br.com.arcasoftware.comercialapi.application.service.trigger.model.EstIteInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.QtdPedRe2Interface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.RegrasPadraoFatInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.RegrasProdutoInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.service.TriggerService;
import br.com.arcasoftwares.model.Pedre2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
public class PedRe2CommonMath {

    private final BigDecimal zero = new BigDecimal(0);
    private final BigDecimal cem = BigDecimal.valueOf(100);


    private final TriggerService service;

    private String rCodigo;

    private String rEx;

    private BigDecimal rAliquota;

    @Autowired
    public PedRe2CommonMath(TriggerService service) {
        this.service = service;
    }

    public void aplicaDesconto(Pedre2 pedre2) {
        if (pedre2.getDsccom().compareTo(zero) > 0) {
            pedre2.setVdscom(pedre2.getDsccom().multiply(pedre2.getVlqre2()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));

            pedre2.setVlqre2(pedre2.getVlqre2().subtract(pedre2.getVdscom()));
            pedre2.setTotdco(pedre2.getVdscom().multiply(pedre2.getQtpre2()));
        }

        /* Percentual de Desconto Vendedor */
        if (pedre2.getVdpre2().compareTo(zero) > 0) {
            pedre2.setDspre2(
                    pedre2.getVdpre2().multiply(cem).divide(pedre2.getVlqre2(), RoundingMode.HALF_DOWN));

            pedre2.setVlqre2(pedre2.getVlqre2().subtract(pedre2.getVdpre2()));
            pedre2.setTotdsp(pedre2.getVdpre2().multiply(pedre2.getQtpre2()));

        } else {
            pedre2.setVdpre2(zero);
            if (pedre2.getDspre2().compareTo(zero) > 0) {
                pedre2.setVdpre2(
                        pedre2.getDspre2().multiply(pedre2.getVlqre2()).divide(cem, RoundingMode.HALF_DOWN));

                pedre2.setVlqre2(pedre2.getVlqre2().subtract(pedre2.getVdpre2()));
                pedre2.setTotdsp(pedre2.getVdpre2().multiply(pedre2.getQtpre2()));

            } else {
                pedre2.setDspre2(zero);
            }

        }
    }

    public void aplicaDescontoPromocional(Pedre2 pedre2)
            throws ValidationException {

        if (pedre2.getDspre2().compareTo(BigDecimal.ZERO) > 0) {

            Integer codPrm = this.service.getCodPrm(pedre2.getId().getDteres());

            if (codPrm != null) {

                QtdPedRe2Interface qtdPedRe2 = this.service.getQtdPedRe2ByIdentificacaoItem(codPrm, pedre2.getCodclp(),
                        pedre2.getCodgru(), pedre2.getCodsub(), pedre2.getCodpro());

                BigDecimal dscprm;
                BigDecimal qtdprm;
                switch (pedre2.getTabprc()) {
                    case 1: {
                        dscprm = qtdPedRe2.getDS1PR2();
                        qtdprm = qtdPedRe2.getQT1PR2();
                        break;
                    }
                    case 2: {
                        dscprm = qtdPedRe2.getDS2PR2();
                        qtdprm = qtdPedRe2.getQT2PR2();
                        break;
                    }
                    case 3: {
                        dscprm = qtdPedRe2.getDS3PR2();
                        qtdprm = qtdPedRe2.getQT3PR2();
                        break;
                    }
                    case 4: {
                        dscprm = qtdPedRe2.getDS4PR2();
                        qtdprm = qtdPedRe2.getQT4PR2();
                        break;
                    }
                    case 5: {
                        dscprm = qtdPedRe2.getDS5PR2();
                        qtdprm = qtdPedRe2.getQT5PR2();
                        break;
                    }
                    default: {
                        dscprm = qtdPedRe2.getDS1PR2();
                        qtdprm = qtdPedRe2.getQT1PR2();
                    }
                }


                if (dscprm == null) {
                    dscprm = zero;
                }

                if (pedre2.getDspre2().compareTo(dscprm) <= 0) {

                    pedre2.setCodprm(codPrm);
                    pedre2.setSeqpr2(qtdPedRe2.getSEQPR2());

                    if (qtdprm.compareTo(zero) > 0) {

                        BigDecimal qtdPed = this.service.getQtdPed(codPrm, pedre2.getSeqpr2(), pedre2.getTabprc());

                        qtdPed = qtdPed.add(pedre2.getQtpre2());

                        if (qtdPed.compareTo(qtdprm) > 0) {
                            throw new ValidationException("EXCEPTION_ERR249");
                        }

                    }

                } else
                    throw new ValidationException("EXCEPTION_ERR248");

            } else
                throw new ValidationException("EXCEPTION_ERR248");

        } else {
            pedre2.setDspre2(zero);
        }
    }

    public void aplicaAcrescimoVendedor(Pedre2 pedre2) {
        if (pedre2.getVacre2().compareTo(zero) > 0) {

            pedre2.setPacre2(
                    pedre2.getVacre2().multiply(cem).divide(pedre2.getVlqre2(), RoundingMode.HALF_DOWN));

            pedre2.setVlqre2(pedre2.getVlqre2().add(pedre2.getVacre2()));
            pedre2.setTotacr(pedre2.getVacre2().multiply(pedre2.getQtpre2()));

        } else {

            pedre2.setVacre2(zero);

            if (pedre2.getPacre2().compareTo(zero) > 0) {

                pedre2.setVacre2(
                        pedre2.getPacre2().multiply(pedre2.getVlqre2()).divide(cem, RoundingMode.HALF_DOWN));

                pedre2.setVlqre2(pedre2.getVlqre2().add(pedre2.getVacre2()));
                pedre2.setTotacr(pedre2.getVacre2().multiply(pedre2.getQtpre2()));

            } else {
                pedre2.setPacre2(zero);
            }

        }
    }

    public void aplicaDescontoVendedor(Pedre2 pedre2) {
        if (pedre2.getDscre2().compareTo(zero) > 0) {

            pedre2.setVdsre2(zero);

            if (pedre2.getDscre2().compareTo(zero) > 0) {

                pedre2.setVdsre2(pedre2.getDscre2().multiply(pedre2.getVlqre2()).divide(cem, RoundingMode.HALF_DOWN));
                pedre2.setVlqre2(pedre2.getVlqre2().subtract(pedre2.getVdsre2()));
                pedre2.setTotdsc(pedre2.getVdsre2().multiply(pedre2.getQtpre2()));

            } else {
                pedre2.setDscre2(zero);
            }

        } else {
            pedre2.setVdsre2(zero);
            pedre2.setDscre2(
                    pedre2.getVdsre2().multiply(cem).divide(pedre2.getVlqre2(), RoundingMode.HALF_DOWN));
            pedre2.setVlqre2(pedre2.getVlqre2().subtract(pedre2.getVdsre2()));
            pedre2.setTotdsc(pedre2.getVdsre2().multiply(pedre2.getQtpre2()));
        }
    }

    public void calculaICMS(Pedre2 pedre2, String consum) {
        pedre2.setBasicm(pedre2.getTotre2().subtract(pedre2.getTotre2()
                .subtract(pedre2.getTotre2().multiply(pedre2.getBscicm()).divide(cem, RoundingMode.HALF_DOWN))));

        pedre2.setBasicm(pedre2.getBasicm()
                .add(pedre2.getTotfrt().subtract(pedre2.getTotfrt()
                        .subtract(pedre2.getTotfrt().multiply(pedre2.getFrticm()).divide(cem, RoundingMode.HALF_DOWN)))));

        pedre2.setBasicm(pedre2.getBasicm()
                .add(pedre2.getTotoutdesp().subtract(pedre2.getTotoutdesp()
                        .subtract(pedre2.getTotoutdesp().multiply(pedre2.getDesicm()).divide(cem, RoundingMode.HALF_DOWN)))));

        pedre2.setBasicm(pedre2.getBasicm()
                .add(pedre2.getTotseg().subtract(pedre2.getTotseg()
                        .subtract(pedre2.getTotseg().multiply(pedre2.getSegicm()).divide(cem, RoundingMode.HALF_DOWN)))));

        pedre2.setBasicm(pedre2.getBasicm()
                .subtract(pedre2.getTotdescinc().subtract((pedre2.getTotdescinc()
                        .subtract((pedre2.getTotdescinc().multiply(pedre2.getDscicm()).divide(cem, RoundingMode.HALF_DOWN)))))));

        // Apenas o flag consum ira indicar se ha incidencia de IPI sobre o ICMS
        if (pedre2.getTotipi().compareTo(zero) > 0 && consum.equalsIgnoreCase("Sim")) {
            pedre2.setBasicm(pedre2.getBasicm().add(pedre2.getTotipi()
                    .subtract(pedre2.getTotipi().subtract(pedre2.getTotipi().multiply(cem)).divide(cem, RoundingMode.HALF_DOWN))));

            pedre2.setBasicm(pedre2.getBasicm().subtract(pedre2.getBasicm()
                    .subtract(pedre2.getBasicm().multiply(pedre2.getBscicm()).divide(cem, RoundingMode.HALF_DOWN))));
        }

        if (pedre2.getRedicm().compareTo(zero) > 0) {
            pedre2.setBasicm(pedre2.getBasicm()
                    .subtract((pedre2.getBasicm().multiply(pedre2.getRedicm()).divide(cem, RoundingMode.HALF_DOWN))));
        }

        pedre2.setToticm(pedre2.getBasicm().multiply(pedre2.getIcmre2()).divide(cem, RoundingMode.HALF_DOWN));
    }

    public void calculaICMSST(Pedre2 pedre2, Integer codtcl, String uferes, String codanp) {
        if ((!new ArrayList<>(Arrays.asList(1, 5)).contains(codtcl))) {

            pedre2.setBassub(
                    pedre2.getTotre2()
                            .subtract(pedre2.getTotre2()
                                    .subtract(pedre2.getTotre2()
                                            .multiply(pedre2.getBasesb())
                                            .divide(cem, RoundingMode.HALF_DOWN))));

            pedre2.setBassub(pedre2.getBassub().add(pedre2.getTotipi()));

            pedre2.setBassub(pedre2.getBassub()
                    .add(pedre2.getTotfrt().subtract(pedre2.getTotfrt()
                            .subtract(pedre2.getTotfrt().multiply(pedre2.getFrtsub()).divide(cem, RoundingMode.HALF_DOWN)))));

            pedre2.setBassub(pedre2.getBassub()
                    .add(pedre2.getTotseg().subtract(pedre2.getTotseg()
                            .subtract(pedre2.getTotseg().multiply(pedre2.getSegsub()).divide(cem, RoundingMode.HALF_DOWN)))));

            pedre2.setBassub(pedre2.getBassub()
                    .add(pedre2.getTotoutdesp().subtract(pedre2.getTotoutdesp()
                            .subtract(pedre2.getTotoutdesp().multiply(pedre2.getDessub()).divide(cem, RoundingMode.HALF_DOWN)))));

            pedre2.setBassub(pedre2.getBassub()
                    .subtract(pedre2.getTotdescinc().subtract(pedre2.getTotdescinc()
                            .subtract(pedre2.getTotdescinc().multiply(pedre2.getDscsub()).divide(cem, RoundingMode.HALF_DOWN)))));

            pedre2.setBassub(pedre2.getBassub().subtract(pedre2.getTotdsr()));

            if (pedre2.getRedsub().compareTo(zero) > 0.0) {
                pedre2.setBassub(pedre2.getBassub()
                        .subtract(pedre2.getBassub().multiply(pedre2.getRedsub()).divide(cem, RoundingMode.HALF_DOWN))); // --Aplica a Reducao
            }

            if ((pedre2.getMrgsub().compareTo(zero) > 0)
                    || pedre2.getBassub().compareTo(pedre2.getBasicm()) > 0) {
                pedre2.setBassub(pedre2.getBassub()
                        .add(pedre2.getBassub().multiply(pedre2.getMrgsub()).divide(cem, RoundingMode.HALF_DOWN)));
                pedre2.setTotsub(pedre2.getBassub().multiply(pedre2.getIcmsub()).divide(cem, RoundingMode.HALF_DOWN)
                        .subtract(pedre2.getToticm()));

            } else {

                if (Arrays.asList("AL", "CE", "DF", "ES", "MA", "MS", "RN", "RR").contains(uferes)) {
                    pedre2.setTotsub(
                            pedre2.getBassub().multiply(pedre2.getIcmsub()).divide(cem, RoundingMode.HALF_DOWN)
                                    .subtract(pedre2.getToticm()));
                } else {
                    pedre2.setTotsub(
                            pedre2.getBassub().subtract(pedre2.getToticm())
                                    .divide(cem.subtract(pedre2.getIcmsub()), RoundingMode.DOWN)
                                    .multiply(cem)
                                    .subtract(pedre2.getBassub())
                    );
                }
                if (pedre2.getTotsub().compareTo(zero) < 0) {
                    pedre2.setTotsub(zero);
                }

            }

            if (uferes.equalsIgnoreCase("RS") && (!codanp.equalsIgnoreCase(""))) {
                pedre2.setTotsub(
                        pedre2.getToticm().add(pedre2.getTotsub()));
            }
        } else {
            pedre2.setBassub(zero);
            pedre2.setTotsub(zero);
        }
    }

    public void carregaDescontosPermitidos(Pedre2 pedre2, BigDecimal ds1Ite, BigDecimal ds2Ite,
                                           BigDecimal ds3Ite, BigDecimal ds4Ite, BigDecimal ds5Ite) {

        if (pedre2.getTabprc() == 1) {
            pedre2.setDscper(ds1Ite);
        }
        if (pedre2.getTabprc() == 2) {
            pedre2.setDscper(ds2Ite);
        }
        if (pedre2.getTabprc() == 3) {
            pedre2.setDscper(ds3Ite);
        }
        if (pedre2.getTabprc() == 4) {
            pedre2.setDscper(ds4Ite);
        }
        if (pedre2.getTabprc() == 5) {
            pedre2.setDscper(ds5Ite);
        }
    }

    public String processaValorMarkup(Pedre2 pedre2, String comtab, String codcm1, String codcm2, String codcm3,
                                      String codcm4, String codcm5, BigDecimal mk1, BigDecimal mk2, BigDecimal mk3, BigDecimal mk4,
                                      BigDecimal mk5) {

        if (pedre2.getMarped().compareTo(mk1) < 0) {
            comtab = codcm1;
        } else if ((pedre2.getMarped().compareTo(mk1) >= 0)
                && (pedre2.getMarped().compareTo(mk2) < 0)) {
            comtab = codcm1;
        } else if ((pedre2.getMarped().compareTo(mk2) >= 0)
                && (pedre2.getMarped().compareTo(mk3) < 0)) {
            comtab = codcm2;
        } else if ((pedre2.getMarped().compareTo(mk3) >= 0)
                && (pedre2.getMarped().compareTo(mk4) < 0)) {
            comtab = codcm3;
        } else if ((pedre2.getMarped().compareTo(mk4) >= 0)
                && (pedre2.getMarped().compareTo(mk5) < 0)) {
            comtab = codcm4;
        } else if (pedre2.getMarped().compareTo(mk5) >= 0) {
            comtab = codcm5;
        }
        return comtab;
    }

    public void processoNF20(Pedre2 pedre2, String tipped, String sufrIcms, String flgipi) {
        if (pedre2.getCodst2().trim().equalsIgnoreCase("60")) {
            pedre2.setBasicm(zero);
            pedre2.setToticm(zero);
            pedre2.setBassub(zero);
            pedre2.setTotsub(zero);
            pedre2.setIcmre2(zero);
            pedre2.setMrgsub(zero);
        }

        if (new ArrayList<>(Arrays.asList("00", "20")).contains(pedre2.getCodst2().trim())) {
            pedre2.setBassub(zero);
            pedre2.setTotsub(zero);
            pedre2.setMrgsub(zero);
            pedre2.setIcmsub(zero);
        }

        if (new ArrayList<>(Arrays.asList("40", "41", "50", "51")).contains(pedre2.getCodst2().trim())) {
            pedre2.setBasicm(zero);
            pedre2.setToticm(zero);
            pedre2.setBassub(zero);
            pedre2.setTotsub(zero);
            pedre2.setIcmsub(zero);
            pedre2.setMrgsub(zero);
        }

        if (null != sufrIcms && sufrIcms.equalsIgnoreCase("S")) {
            pedre2.setBasicm(zero);
            pedre2.setToticm(zero);
        }

        calculateForNFeSimples(pedre2);

        if (tipped.equalsIgnoreCase("1")) {
            pedre2.setTotge2(pedre2.getTotge2().add(pedre2.getTotipi())
                    .add(pedre2.getTotsub()));
        }

        pedre2.setTotper(pedre2.getQtpre2()
                .multiply(pedre2.getVlure2().multiply(pedre2.getDscper()).divide(cem, RoundingMode.HALF_DOWN)));

        if (pedre2.getTotper().compareTo(zero) > 0) {
            pedre2.setDifdsc(pedre2.getTotdsc().multiply(cem).divide(pedre2.getTotper(), RoundingMode.HALF_DOWN).subtract(cem));
        }

        if (pedre2.getTotcst().compareTo(zero) > 0) {

            pedre2.setMarpre(pedre2.getQtpre2()
                    .multiply(pedre2.getVlure2().subtract(pedre2.getVdrre2()))
                    .subtract(pedre2.getTotcst()).multiply(cem).divide(pedre2.getTotcst(), RoundingMode.HALF_DOWN));

            if (flgipi.equalsIgnoreCase("Sim")) {
                pedre2.setMarped(pedre2.getTotre2().subtract(pedre2.getTotdsr())
                        .subtract(pedre2.getTotcst()).multiply(cem).divide(pedre2.getTotcst(), RoundingMode.HALF_DOWN));
            } else {
                pedre2.setMarped(
                        pedre2.getTotre2().subtract(pedre2.getTotdsr())
                                .subtract(pedre2.getTotipc()).subtract(pedre2.getTotcst()).multiply(cem)
                                .divide(pedre2.getTotcst(), RoundingMode.HALF_DOWN));
            }

        }

        if (flgipi.equalsIgnoreCase("Sim")) {
            pedre2.setLucrol(pedre2.getTotre2().subtract(pedre2.getTotdsr())
                    .subtract(pedre2.getTotcst()));
        } else {
            pedre2.setLucrol(pedre2.getTotre2().subtract(pedre2.getTotdsr()
                    .subtract(pedre2.getTotipc()).subtract(pedre2.getTotcst())));
        }

        pedre2.setLucrop(pedre2.getQtpre2()
                .multiply(pedre2.getVlure2().subtract(pedre2.getVdrre2()))
                .subtract(pedre2.getTotcst()));

        pedre2.setTotper(pedre2.getQtpre2()
                .multiply(pedre2.getVlure2().subtract(pedre2.getVdrre2()))
                .subtract(pedre2.getTotper()));

        if (flgipi.equalsIgnoreCase("Sim")) {
            pedre2.setBascom(pedre2.getTotre2().subtract(pedre2.getTotdsr()));
        } else {
            pedre2.setBascom(pedre2.getTotre2().subtract(pedre2.getTotdsr())
                    .subtract(pedre2.getTotipc()));
        }
    }

    private void calculateForNFeSimples(Pedre2 pedre2) {
        /* NF-e Simples */
        if (new ArrayList<>(Arrays.asList("102", "103", "300", "400", "500")).contains(pedre2.getCodst2().trim())) {
            pedre2.setBasicm(zero);
            pedre2.setToticm(zero);
            pedre2.setBassub(zero);
            pedre2.setTotsub(zero);
            pedre2.setIcmre2(zero);
            pedre2.setIcmsub(zero);
            pedre2.setMrgsub(zero);

        }

        /* NF-e Simples */
        if (new ArrayList<>(Arrays.asList("202", "203")).contains(pedre2.getCodst2().trim())) {
            pedre2.setBasicm(zero);
            pedre2.setToticm(zero);
            pedre2.setIcmre2(zero);
        }
    }

    public void processaDescZfCof(Pedre2 pedre2, String sufrCofins, TipEmpEnum tipemp) {

        // Calculo para Desconto de ZF
        if (pedre2.getFlgDescZfCof() && null != sufrCofins && sufrCofins.equalsIgnoreCase("S") && !new ArrayList<>(Arrays.asList("07", "08")).contains(pedre2.getCstcof())) // ST que nao
        // tem
        // tributacao
        {
            pedre2.setTotcofzf(zero);
            if (tipemp != TipEmpEnum.SIMPLES_NACIONAL) {
                pedre2.setTotcofzf(pedre2.getTotre2().subtract(pedre2.getTotre2()
                        .subtract(pedre2.getTotre2().multiply(pedre2.getAliqcofZf()).divide(cem, RoundingMode.HALF_DOWN))));
            }

            if (new ArrayList<>(Arrays.asList("6.109", "6.110")).contains(pedre2.getCodcfo())) {
                pedre2.setTotdsr(pedre2.getTotdsr().add(pedre2.getTotcofzf()));
            }

            pedre2.setTotcof(zero);
            pedre2.setAliqcof(zero);
        }
    }

    public void processaDescZfPis(Pedre2 pedre2, String sufrPis, TipEmpEnum tipemp) {
        // Calculo para Desconto de ZF
        if (pedre2.getFlgDescZfPis() && null != sufrPis && sufrPis.equalsIgnoreCase("S") && !new ArrayList<>(Arrays.asList("07", "08")).contains(pedre2.getCstpis())) // ST que nao
        // tem
        // tributacao
        {

            pedre2.setTotpiszf(zero);
            if (tipemp != TipEmpEnum.SIMPLES_NACIONAL) {
                pedre2.setTotpiszf(pedre2.getTotre2().subtract(pedre2.getTotre2()
                        .subtract(pedre2.getTotre2().multiply(pedre2.getAliqpisZf()).divide(cem, RoundingMode.HALF_DOWN))));
            }

            if (new ArrayList<>(Arrays.asList("6.109", "6.110")).contains(pedre2.getCodcfo())) {
                pedre2.setTotdsr(pedre2.getTotdsr().add(pedre2.getTotpiszf()));
            }

            pedre2.setTotpis(zero);
            pedre2.setAliqpis(zero);
        }
    }

    public void processaQtdPorEmbalagem(Pedre2 pedre2, ModPfaEnum modpfa, BigDecimal qtdemb)
            throws ValidationException {
        int qtdreg;
        if (qtdemb != null) {

            pedre2.setQtdemb(qtdemb);

            if (pedre2.getId().getCodemp() == 1 && modpfa.equals(ModPfaEnum.VENDAS) &&
                    !pedre2.getFlgfec() && pedre2.getQtdemb().intValue() > 1) {
                if (pedre2.getQtpre2().compareTo(pedre2.getQtdemb()) >= 0) {

                    qtdreg = (int) (pedre2.getQtpre2().doubleValue() % pedre2.getQtdemb().doubleValue());
                    if (qtdreg > 0) {
                        throw new ValidationException(
                                "Quantidade informada inconsistente por embalagem. Item_ "
                                        + pedre2.getCodgru() + "." + pedre2.getCodsub() + "."
                                        + pedre2.getCodpro());
                    }

                } else {
                    throw new ValidationException("Quantidade informada inconsistente por embalagem. Item_ "
                            + pedre2.getCodgru() + "." + pedre2.getCodsub() + "." + pedre2.getCodpro());
                }
            }

        } else {
            pedre2.setQtdemb(new BigDecimal(0));
        }
    }

    public void insertOperations(Pedre2 pedre2) throws ValidationException {

        Integer codemp = pedre2.getId().getCodemp();
        Date dteres = pedre2.getId().getDteres();
        Integer numres = pedre2.getId().getNumres();

        pedre2.setIdPedres(pedre2.getId().getNumres());

        String sitped = this.service.getSitLojPed(pedre2.getId().getNumres()).getSitPed();

        if (sitped.equalsIgnoreCase("Aguard&&o Recebimento pelo Caixa") || sitped.equalsIgnoreCase("Recebida")) {
            throw new ValidationException(
                    "Com&&a em processo de recebimento. Verifique com o caixa e tente novamente.");
        }

        if (pedre2.getIsCta() == null) {
            pedre2.setIsCta(0);
        }

        /* EMPPED = Misterio !!! Ta sempre null */
        if (pedre2.getEmpped() != null) {
            pedre2.setFlgdup("Nao");
        }

        if (pedre2.getFlgdup().equalsIgnoreCase("Nao")) { /* FLGDUP = Flag para bloquear item duplicado */

            Integer qtdreg = this.service.getTotalItens(pedre2.getId().getCodemp(), pedre2.getId().getDteres(),
                    pedre2.getId().getNumres(), pedre2.getCodclp(), pedre2.getCodgru(), pedre2.getCodsub(),
                    pedre2.getCodpro());

            if (qtdreg > 0) {
                throw new ValidationException("EXCEPTION_ERR219");
            }

        }

        if (pedre2.getCbare2() == null) {

            pedre2.setCbare2(this.service.getCodBarByProduto(pedre2.getCodclp(), pedre2.getCodgru(), pedre2.getCodsub(),
                    pedre2.getCodpro()));

        }
        if (this.service.getTipPedFromParametro().equalsIgnoreCase("Codigo de Barras") && pedre2.getCbare2() == null) {
            throw new ValidationException("EXCEPTION_ERR284");
        }

        RegrasPadraoFatInterface regraPfa = this.service.getRegrasPadraoFatByPedido(pedre2.getId().getCodemp(),
                pedre2.getId().getDteres(), pedre2.getId().getNumres());

        pedre2.setDscreg(regraPfa.getDSCREG());

        pedre2.setFrticm(regraPfa.getFRTICM());
        pedre2.setSegicm(regraPfa.getSEGICM());
        pedre2.setDesicm(regraPfa.getDESICM());
        pedre2.setDscicm(regraPfa.getDSCICM());

        pedre2.setFrtipi(regraPfa.getFRTIPI());
        pedre2.setSegipi(regraPfa.getSEGIPI());
        pedre2.setDesipi(regraPfa.getDESIPI());
        pedre2.setDscipi(regraPfa.getDSCIPI());

        pedre2.setFrtsub(regraPfa.getFRTSUB());
        pedre2.setSegsub(null != regraPfa.getSEGSUB() ? regraPfa.getSEGSUB() : BigDecimal.ZERO);
        pedre2.setDessub(regraPfa.getDESSUB());
        pedre2.setDscsub(regraPfa.getDSCSUB());

        TipComEnum tipcom = TipComEnum.getEnumFromDescription(regraPfa.getTIPCOM().trim());
        String flgtab = regraPfa.getFLGTAB().trim();
        FlgTabEnum flgTabEnum = FlgTabEnum.getEnumFromDescription(flgtab);

        pedre2.setMesres(regraPfa.getMESRES());
        pedre2.setTipcpa(regraPfa.getTIPCPA());
        pedre2.setAnores(regraPfa.getANORES());
        pedre2.setFlgres(regraPfa.getFLGRES());
        pedre2.setNrore2(regraPfa.getQTIRES() + 1);

        /* Se ha Desconto Comercial */
        if (regraPfa.getDSCCOM().compareTo(zero) > 0) {
            pedre2.setDsccom(regraPfa.getDSCCOM());
        }

        /* EMPGER ???? */
        if (pedre2.getEmpger() == null) {
            pedre2.getId().setSeqre2(this.service.getMaxSeqRe2(pedre2.getId().getCodemp(), pedre2.getId().getDteres(),
                    pedre2.getId().getNumres()) + 1);
        }

        /* Busca Regras de ICMS, IPI, ST, ISS e outros no Produto */
        RegrasProdutoInterface regraProd = this.service.getRegrasProdutoByIdentificacao(pedre2.getCodclp(),
                pedre2.getCodgru(), pedre2.getCodsub(), pedre2.getCodpro());

        String flbpro = regraProd.getFLBPRO();

        pedre2.setCodcat(regraProd.getCODCAT());
        pedre2.setLiqre2(regraProd.getPESLIQ());
        pedre2.setCodtip(regraProd.getCODTIP());
        pedre2.setBrtre2(regraProd.getPESBRT());
        pedre2.setCodmrc(regraProd.getCODMRC());
        pedre2.setVolre2(regraProd.getQTDVOL());
        pedre2.setCodund(regraProd.getCODUND());
        pedre2.setRefre2(regraProd.getREFPRO());

        pedre2.setIssre2(regraProd.getISSPRO());

        pedre2.setTotfrt(pedre2.getTotfrt());
        pedre2.setTotoutdesp(pedre2.getTotoutdesp());
        pedre2.setTotseg(pedre2.getTotseg());
        pedre2.setTotdsr(pedre2.getTotdsr());
        pedre2.setTotdescinc(pedre2.getTotdescinc());

        if (pedre2.getCodst1() == null) {
            throw new ValidationException("O item nao tem ORIGEM DO PRODUTO cadastrada");
        }
        if (pedre2.getIssre2() == null) {
            pedre2.setIssre2(new BigDecimal(0));
        }

        if (pedre2.getIssre2().compareTo(zero) > 0) {

            Integer qtdreg = this.service.getTotalItensISSR2(codemp, dteres, numres);

            if (qtdreg == 4) {
                throw new ValidationException("EXCEPTION_ERR278");
            }

        }

        if (pedre2.getFlgfec()) {
            flbpro = " ";
        }
        if (flbpro.equalsIgnoreCase("*")) {
            throw new ValidationException("Operacao nao pode ser realizada. Item descontinuado_ " + pedre2.getCodclp()
                    + "." + pedre2.getCodgru() + "." + pedre2.getCodsub() + "." + pedre2.getCodpro());
        }

        if (!pedre2.getFlgfec() && pedre2.getDesre2() == null) {
            pedre2.setDesre2(regraProd.getDSCPRO());
        }

        /* Serao Carregadas no ESTITE */
        pedre2.setDscper(zero);
        pedre2.setVcpcst(zero);
        pedre2.setVchcst(zero);
        pedre2.setVprcst(zero);
        pedre2.setVrecst(zero);
        pedre2.setVmecst(zero);
        pedre2.setVcrcst(zero);
        pedre2.setVpfcst(zero);

        /* Carrega os dados da ESTITE */
        EstIteInterface estiteDados = this.service.getestIteByIdentificacao(codemp, pedre2.getCodclp(),
                pedre2.getCodgru(), pedre2.getCodsub(), pedre2.getCodpro());

        setValuesPesRe2(pedre2, estiteDados);

        /* Se NAO for Fechamento de Vales, Seta o Flag de Custo */
        if (!pedre2.getFlgfec()) {

            pedre2.setVcsre2(zero);

            switch (flgTabEnum) {
                case ULTIMO_PRECO: {
                    pedre2.setVcsre2(pedre2.getCstre2());
                    break;
                }
                case CUSTO_HISTORICO: {
                    pedre2.setVcsre2(pedre2.getVchre2());
                    break;
                }
                case CUSTO_PONDERADO: {
                    pedre2.setVcsre2(pedre2.getVcpre2());
                    break;
                }
                case CUSTO_REFERENCIAL: {
                    pedre2.setVcsre2(pedre2.getVcrre2());
                    break;
                }
                case ULTIMO_CUSTO_CIF: {
                    pedre2.setVcsre2(pedre2.getVpfout());
                    break;
                }
                case CUSTO_HISTORICO_G: {
                    pedre2.setVcsre2(pedre2.getVrere2());
                    break;
                }
                case CUSTO_PONDERADO_G: {
                    pedre2.setVcsre2(pedre2.getVprre2());
                    break;
                }
                case MEDIA_PONDERADA_E: {
                    pedre2.setVcsre2(pedre2.getVmere2());
                    break;
                }
                case CUSTO_ULTIMA_COMPRA: {
                    pedre2.setVcsre2(pedre2.getVpfre2());
                    break;
                }
                default:
                    pedre2.setVcsre2(pedre2.getCstre2());
            }

            if (regraPfa.getLANEST().equalsIgnoreCase("Sim") && regraPfa.getCODFIL() != null) {
                switch (flgTabEnum) {

                    case ULTIMO_PRECO: {
                        pedre2.setVcsre2(pedre2.getCstcst());
                        break;
                    }
                    case CUSTO_HISTORICO: {
                        pedre2.setVcsre2(pedre2.getVchcst());
                        break;
                    }
                    case CUSTO_PONDERADO: {
                        pedre2.setVcsre2(pedre2.getVcpcst());
                        break;
                    }
                    case CUSTO_REFERENCIAL: {
                        pedre2.setVcsre2(pedre2.getVcrcst());
                        break;
                    }
                    case ULTIMO_CUSTO_CIF: {
                        pedre2.setVcsre2(pedre2.getOutcst());
                        break;
                    }
                    case CUSTO_HISTORICO_G: {
                        pedre2.setVcsre2(pedre2.getVrecst());
                        break;
                    }
                    case CUSTO_PONDERADO_G: {
                        pedre2.setVcsre2(pedre2.getVprcst());
                        break;
                    }
                    case MEDIA_PONDERADA_E: {
                        pedre2.setVcsre2(pedre2.getVmecst());
                        break;
                    }
                    case CUSTO_ULTIMA_COMPRA: {
                        pedre2.setVcsre2(pedre2.getVpfcst());
                        break;
                    }
                    default:
                        pedre2.setVcsre2(pedre2.getCstcst());
                }
            }
        }

        if (pedre2.getVcsre2() == null) {
            pedre2.setVcsre2(zero);
        }

        /* LANCST ??? */
        if (!pedre2.getFlgfec()) {

            if (pedre2.getVcsre2().compareTo(zero) > 0) {
                pedre2.setLancst("Nao");
            } else {
                pedre2.setLancst("Sim");
            }

        }

        if (pedre2.getDsrre2().compareTo(zero) > 0 && !pedre2.getCodst1().equalsIgnoreCase("0")) {

            if (pedre2.getIsCta() == 0) {
                pedre2.setIcmre2(pedre2.getDsrre2());
            }

            pedre2.setTrbicm("Sim");
        }

        if (new ArrayList<>(
                Arrays.asList("60", "101", "102", "103", "201", "201", "203", "300", "400", "500", "900"))
                .contains(pedre2.getCodst2())) {
            pedre2.setDsrre2(zero);
        }

        estiteDados = this.service.getestIteByIdentificacao(codemp, pedre2.getCodclp(), pedre2.getCodgru(),
                pedre2.getCodsub(), pedre2.getCodpro());

        /* Caso esse pedido venha de cotacao, manter o preco da cotacao */
        /* EMPCTA = Empresa da Cotacao */

        if (regraPfa.getEMPCTA() != null && regraPfa.getPRCCTA().equalsIgnoreCase("Sim")) {
            pedre2.setVlure2(zero);
        }
        setValuesPesRe2(pedre2, estiteDados);

        /* Verifica a Comissao */
        if (!pedre2.getFlgfec()) /* Caso nao for Fechamento de Vale */ {
            pedre2.setCodcom(regraPfa.getCOMPED());

            if (tipcom == TipComEnum.ITEM) {
                pedre2.setCodcom(regraProd.getCODCOM());

                if (pedre2.getCodcom().isEmpty()) {
                    throw new ValidationException("Comissao por produto nao informado.");
                }
            }

            if ((tipcom == TipComEnum.TABELA_PRECO) || (tipcom == TipComEnum.VALOR_MARKUP) && pedre2.getCodcom().isEmpty()) {
                throw new ValidationException("Comissao por tabela nao informado..");
            }

            if (tipcom == TipComEnum.CLIENTE) {
                if (regraPfa.getCODCLI().compareTo(0) > 0) {
                    pedre2.setCodcom(regraPfa.getCOMCLI());
                    if (pedre2.getCodcom().isEmpty()) {
                        throw new ValidationException("Comissao por cliente nao informado.");
                    }
                } else {
                    throw new ValidationException(
                            "Nao ha cliente informado e comissao necessita de um cliente valido.");
                }
            }

            // Se a Comissao for por "Vendedor" ja esta resolvida no Bef.Ins. do Cabecalho
            if (tipcom == TipComEnum.VENDEDOR && pedre2.getCodcom().isEmpty())
                throw new ValidationException("Comissao por vendedor nao informado.");

        }
        if (pedre2.getCodcom() == null) {
            throw new ValidationException("Comissao por " + tipcom + " nao informado.");
        }

        if (pedre2.getTipcpa().equalsIgnoreCase("Atendente") && regraPfa.getCODATD() != null) {
            pedre2.setPcoatd(pedre2.getPcore2());
            pedre2.setPcore2(zero);
        }

        if (rCodigo == null) {
            rCodigo = pedre2.getClsipi();
        }
        if (rEx == null) {
            rEx = " ";
        }
        if (rAliquota == null) {
            if (new ArrayList<>(Arrays.asList("0", "3", "4", "5")).contains(pedre2.getCodst1())) {
                rAliquota = BigDecimal.valueOf(32.09);
            } else {
                rAliquota = BigDecimal.valueOf(40.69);
            }
        }
        /* Fim da Alteracao do Sergio */

        pedre2.setCodigoIbpt(rCodigo);
        pedre2.setExIbpt(rEx);
        pedre2.setAliqIbpt(rAliquota);

        pedre2.setTotibpt(pedre2.getTotgeral().multiply(pedre2.getAliqIbpt().divide(cem, RoundingMode.HALF_DOWN)));

    }

    private void setValuesPesRe2(Pedre2 pedre2, EstIteInterface estiteDados) {
        pedre2.setCstre2(estiteDados.getCSTITE());
        pedre2.setVrecst(estiteDados.getVREITE());
        pedre2.setVchre2(estiteDados.getVCHITE());
        pedre2.setVcrcst(estiteDados.getVCRITE());
        pedre2.setVrere2(estiteDados.getVREITE());
        pedre2.setVcpcst(estiteDados.getVCPITE());
        pedre2.setVcrre2(estiteDados.getVCRITE());
        pedre2.setVprcst(estiteDados.getVPRITE());
        pedre2.setVcpre2(estiteDados.getVCPITE());
        pedre2.setOutcst(estiteDados.getVPFOUT());
        pedre2.setVprre2(estiteDados.getVPRITE());
        pedre2.setVmere2(estiteDados.getVMEITE());
        pedre2.setCstcst(estiteDados.getCSTITE());
        pedre2.setVpfre2(estiteDados.getVPFITE());
        pedre2.setVchcst(estiteDados.getVCHITE());
        pedre2.setVpfout(estiteDados.getVPFOUT());
    }
}
