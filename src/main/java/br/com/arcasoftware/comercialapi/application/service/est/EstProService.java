package br.com.arcasoftware.comercialapi.application.service.est;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.est.*;
import br.com.arcasoftware.comercialapi.application.repository.ger.GerProRepository;
import br.com.arcasoftware.comercialapi.application.service.trigger.pedre2.TipEmpEnum;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftware.comercialapi.model.ProcessamentoDadosEntrada;
import br.com.arcasoftware.comercialapi.model.ProcessamentoDadosSaida;
import br.com.arcasoftware.comercialapi.utils.ObjectUtils;
import br.com.arcasoftwares.model.*;
import br.com.arcasoftwares.model.dto.IEstProDTO;
import br.com.arcasoftwares.model.interfaces.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EstProService implements RestBasicService<Estpro, IEstProDTO> {

    static final Integer DSCREG = 0;

    String cest;
    String codRegraFcp;

    Integer idEstIcm;
    String icmsCst;
    final BigDecimal aliqCred = new BigDecimal(0);

    static final String CNAE = "";
    BigDecimal fcpAliqFcpUfDest;
    Integer idRegraFcp;

    String strCodTxf;
    String icmsCodTxf;

    BigDecimal difalAliqIcmsUfDest;
    BigDecimal difalAliqIcmsInter;

    @Autowired
    private EstProRepository repository;

    @Autowired
    private GerProRepository gerProRepository;

    @Autowired
    private EstPfaRepository estPfaRepository;

    @Autowired
    private EstIcmRepository estIcmRepository;

    @Autowired
    private EstUfeRepository estUfeRepository;

    @Autowired
    private EstIpiRepository estIpiRepository;

    public List<IEstProDTO> getAll() {
        return repository.getAllDTO();
    }

    public ProcessamentoDadosSaida processarRegrasItem(ProcessamentoDadosEntrada proBasicRegras, Pedres pedres) {

        ProcessamentoDadosSaida prodProcesso = new ProcessamentoDadosSaida();
        prodProcesso.setCodClp(proBasicRegras.getCodClp());
        prodProcesso.setCodGru(proBasicRegras.getCodGru());
        prodProcesso.setCodSub(proBasicRegras.getCodSub());
        prodProcesso.setCodPro(proBasicRegras.getCodPro());

        loadMainRulesFromTheItem(proBasicRegras, pedres, prodProcesso);

        /*
          produto com situacao especial por estado
         */
        List<GerPro> gerProList = this.gerProRepository.getByIdentificador(pedres.getUfecli(),
                proBasicRegras.getCodClp(), proBasicRegras.getCodGru(), proBasicRegras.getCodSub(),
                proBasicRegras.getCodPro());

        if (ObjectUtils.isCollectionValid(gerProList)) {
            prodProcesso.setIcmsRegra(gerProList.get(0).getRegicm());
            prodProcesso.setIcmsTipo(gerProList.get(0).getTipicm());
        }

        Estpfa estpfa = loadEstPfaInfo(pedres, prodProcesso);

        /*
          Regra de ICMS
         */
        Esticm esticm = this.estIcmRepository
                .getByIdentificador(prodProcesso.getIcmsTipo(), prodProcesso.getIcmsRegra(), proBasicRegras.getUfeEmp(),
                        pedres.getRegtrbemp())
                .orElseThrow(() -> new ValidationException(EnumException.ESTICM_NOT_FOUND));
        prodProcesso.setIcmsAliq(esticm.getPericm());
        prodProcesso.setIcmsBase(getIcmbase(esticm));
        prodProcesso.setIcmsRed(esticm.getRedicm());
        prodProcesso.setIcmsIncRev(esticm.getIncrev());
        prodProcesso.setIcmsIncFin(esticm.getIncfin());
        prodProcesso.setCfop(estpfa.getCodcf1());

        this.idEstIcm = esticm.getIdEsticm();
        this.icmsCst = esticm.getCodst2();

        /*
          Sendo Simples Nacional havendo crédito ICMS CST passa para 101
         */
        alteraICMSST(proBasicRegras, esticm, pedres);

        prodProcesso.setCstIcms(icmsCst);

        loadAndProcessRegraSubstituicaoTrib(proBasicRegras, pedres, prodProcesso, estpfa, esticm);

        fcpAliqFcpUfDest = new BigDecimal(0);

        if (StringUtils.hasLength(codRegraFcp)) {
            Optional<FcpInterface> fcp = this.estIcmRepository.getFcp(codRegraFcp.trim(), pedres.getUfecli());
            if (fcp.isPresent()) {
                fcpAliqFcpUfDest = fcp.get().getAliqFcp();
                idRegraFcp = fcp.get().getIdRegraFcp();
            }
        }

        setDIFALAliq(proBasicRegras, prodProcesso, estpfa);

        if (!StringUtils.hasLength(prodProcesso.getCstIcms())) {
            throw new ValidationException(EnumException.ESTICM_NOT_FOUND);
        }

        if (prodProcesso.getCstIcms().equalsIgnoreCase("10") || prodProcesso.getCstIcms().equalsIgnoreCase("60")
                || prodProcesso.getCstIcms().equalsIgnoreCase("70")
                || prodProcesso.getCstIcms().equalsIgnoreCase("500")) {
            /*
              Se ha texto fiscal na Regra de ST
             */
            if (StringUtils.hasLength(strCodTxf)) {
                icmsCodTxf = strCodTxf;
            }

            setCFOPForTransfers(pedres, prodProcesso, estpfa);
        }

        /*
          Valida Regra IPI
         */
        Estipi estipi = this.estIpiRepository.getByIdentificador(prodProcesso.getIpiTipo(), prodProcesso.getIpiRegra())
                .orElseThrow(() -> new ValidationException(EnumException.ESTIPI_NOT_FOUND));

        prodProcesso.setIpiCst(estipi.getCstipi());
        prodProcesso.setIpiAliq(estipi.getPeripi());
        prodProcesso.setIpiBase(estipi.getBasipi());
        prodProcesso.setIpiPerImp(estipi.getPerimp());

        prodProcesso.setPisCst(estipi.getCstpis());
        prodProcesso.setPisAliq(estipi.getAliqPis());
        prodProcesso.setPisFlgDescZf(estipi.getFlgDescZfPis());

        prodProcesso.setCofCst(estipi.getCstcof());
        prodProcesso.setCofAliq(estipi.getAliqCof());
        prodProcesso.setCofFlgDescZf(estipi.getFlgDescZfCof());

        setPisCofinsFromEstPfa(prodProcesso, estpfa);

        if (!StringUtils.hasLength(estipi.getFlgSineif20())) {
            estipi.setFlgSineif20("N");
        }

        loadDIFAL(proBasicRegras, pedres, prodProcesso);

        return prodProcesso;
    }

    private void setCFOPForTransfers(Pedres pedres, ProcessamentoDadosSaida prodProcesso, Estpfa estpfa) {
    /*
      Instrucao abaixo especifica para Transferencias O Comercial NAO DEVE ser
      usado para transferencias
     */
        if (pedres.getCodtcl() != 1 && estpfa.getModpfa().equalsIgnoreCase("Transferencias")) {
            if (prodProcesso.getCfop().startsWith("5.4") || prodProcesso.getCfop().equalsIgnoreCase("5.152")) {
                prodProcesso.setCfop("5.409");
            }

            if (prodProcesso.getCfop().startsWith("6.4") || prodProcesso.getCfop().equalsIgnoreCase("6.152")) {
                prodProcesso.setCfop("6.409");
            }
        }
    }

    private void setDIFALAliq(ProcessamentoDadosEntrada proBasicRegras, ProcessamentoDadosSaida prodProcesso, Estpfa estpfa) {
    /*
      DIFAL - Diferencial de Alíquota do ICMS
     */
        if (DSCREG > 0) {
            prodProcesso.setStrAliq(new BigDecimal(0));
            prodProcesso.setStrMva(new BigDecimal(0));
            prodProcesso.setStrBase(new BigDecimal(0));

            if (proBasicRegras.getTipEmp() != TipEmpEnum.SIMPLES_NACIONAL) {
                if (prodProcesso.getCstIcms().equalsIgnoreCase("10")
                        || prodProcesso.getCstIcms().equalsIgnoreCase("70")) {
                    prodProcesso.setCstIcms("40");
                    prodProcesso.setCfop(estpfa.getCodcf1());
                }
            } else {
                prodProcesso.setCstIcms("400");
                prodProcesso.setCfop(estpfa.getCodcf1());
            }
        }
    }

    private void setPisCofinsFromEstPfa(ProcessamentoDadosSaida prodProcesso, Estpfa estpfa) {
    /*
      TRBPIS Informa se Calculo sera realizado com informacoes do PFA ou do Produto
      Sim = Produto Nao = PFA
     */
        if (estpfa.getTrbpis().equalsIgnoreCase("Nao")) {
            prodProcesso.setPisCst(estpfa.getCstpis());
            prodProcesso.setPisAliq(estpfa.getAliqpis());
        }

        if (estpfa.getTrbcof().equalsIgnoreCase("Nao")) {
            prodProcesso.setCofCst(estpfa.getCstcof());
            prodProcesso.setCofAliq(estpfa.getAliqcof());
        }
    }

    private void loadDIFAL(ProcessamentoDadosEntrada proBasicRegras, Pedres pedres, ProcessamentoDadosSaida prodProcesso) {
        if (!pedres.getUfecli().equalsIgnoreCase(proBasicRegras.getUfeEmp())
                && (!pedres.getUfecli().equalsIgnoreCase("EX"))) {

            Optional<GerIcmInterface> gerIcm = this.estIcmRepository.getGerIcm(pedres.getUfecli());

            if (gerIcm.isPresent()) {
                if (!prodProcesso.getOrigem().equalsIgnoreCase("1") && (!prodProcesso.getOrigem().equalsIgnoreCase("2"))
                        && (!prodProcesso.getOrigem().equalsIgnoreCase("3"))
                        && (!prodProcesso.getOrigem().equalsIgnoreCase("8"))) {
                    prodProcesso.setIcmsAliq(gerIcm.get().getPerIcm());
                    difalAliqIcmsUfDest = gerIcm.get().getAliqInterna();
                    difalAliqIcmsInter = gerIcm.get().getPerIcm();
                } else {
                    prodProcesso.setIcmsAliq(gerIcm.get().getRedInt());
                    difalAliqIcmsUfDest = gerIcm.get().getAliqInterna();
                    difalAliqIcmsInter = gerIcm.get().getRedInt();
                }
            }
        }
    }

    private void loadAndProcessRegraSubstituicaoTrib(ProcessamentoDadosEntrada proBasicRegras, Pedres pedres, ProcessamentoDadosSaida prodProcesso, Estpfa estpfa, Esticm esticm) {
    /*
      Regra Substituição Tributária
     */

        if (StringUtils.hasLength(prodProcesso.getStrRegra())) {
            Optional<EstUfeBasicInfo> estUfeBasicInfo = this.estUfeRepository.getByIdentificador(
                    Integer.parseInt(prodProcesso.getOrigem()), prodProcesso.getStrRegra(), prodProcesso.getStrTipo(),
                    pedres.getUfecli(), proBasicRegras.getUfeEmp(), pedres.getRegtrbemp());

            BigDecimal strMrgMvaSn = new BigDecimal(0);
            BigDecimal strMrgMvaSnSinief = new BigDecimal(0);

            /*
              Valida Regra Substituição
             */
            if (estUfeBasicInfo.isPresent()) {
                EstUfeBasicInfo ufeBasicInfo = estUfeBasicInfo.get();
                prodProcesso.setStrAliq(ufeBasicInfo.getIcmsub());
                prodProcesso.setStrMva(ufeBasicInfo.getMrgmva());
                prodProcesso.setStrBase(ufeBasicInfo.getBasesb().doubleValue() <= 100 ? new BigDecimal(100)
                        : ufeBasicInfo.getBasesb());
                prodProcesso.setStrRed(ufeBasicInfo.getRedsub());
                String strCst = ufeBasicInfo.getCodst2();
                String strCodCfo = ufeBasicInfo.getCodcfo();
                strCodTxf = ufeBasicInfo.getCodtxf();
                strMrgMvaSn = ufeBasicInfo.getmrgmvaSn();
                strMrgMvaSnSinief = ufeBasicInfo.getMrgmvSnSinief();
                /*
                  Sendo Simples Nacional havendo crédito ICMS CST passa para 101
                 */
                alteraICMSST(proBasicRegras, esticm, pedres);
                prodProcesso.setCstIcms(strCst.trim());
                prodProcesso.setCfop(strCodCfo.trim());
            }

            setMVASN(pedres, prodProcesso, strMrgMvaSn, strMrgMvaSnSinief);

            /*
              CNAE MT
             */
            if (StringUtils.hasLength(CNAE) && pedres.getUfecli().equalsIgnoreCase("MT")) {
                Optional<CnaeInterface> cnae2 = this.estIcmRepository.getCnae(CNAE);
                /*
                  Valida Regra Substituição
                 */
                cnae2.ifPresent(cnaeInterface -> prodProcesso.setStrMva(cnaeInterface.getCnaeCargaMedia()));
            }

            if (pedres.getCodtcl() <= 0) {
                return;
            }

            processConsumidorFinal(proBasicRegras, pedres, prodProcesso, estpfa);

            processMVA(proBasicRegras, pedres, prodProcesso, estpfa);

            if (proBasicRegras.getCodTfo() == 2) {

                    /*
                      Revenda
                     */
                prodProcesso.setStrAliq(new BigDecimal(0));
                prodProcesso.setStrMva(new BigDecimal(0));
                prodProcesso.setStrBase(new BigDecimal(0));

                setAliqForSimplesNacional(proBasicRegras, prodProcesso, estpfa);
            }
        }
    }

    private void processMVA(ProcessamentoDadosEntrada proBasicRegras, Pedres pedres, ProcessamentoDadosSaida prodProcesso, Estpfa estpfa) {
        if (!proBasicRegras.getSistema().toUpperCase().contains("COMPRAS")) {
            processIfNotCompras(proBasicRegras, pedres, prodProcesso, estpfa);
        } else {
            if (pedres.getFlgctb().equalsIgnoreCase("Nao")) {
                prodProcesso.setStrMva(new BigDecimal(0));
            }
        }
    }

    private void setAliqForSimplesNacional(ProcessamentoDadosEntrada proBasicRegras, ProcessamentoDadosSaida prodProcesso, Estpfa estpfa) {
        if (proBasicRegras.getTipEmp() != TipEmpEnum.SIMPLES_NACIONAL) {
            if (prodProcesso.getCstIcms().equalsIgnoreCase("10")
                    || prodProcesso.getCstIcms().equalsIgnoreCase("70")) {
                prodProcesso.setCstIcms("00");
                prodProcesso.setCfop(estpfa.getCodcf1());
            } else {
                if (prodProcesso.getCstIcms().equalsIgnoreCase("201")) {
                    prodProcesso.setCstIcms("101");
                    prodProcesso.setCfop(estpfa.getCodcf1());
                }

                if (prodProcesso.getCstIcms().equalsIgnoreCase("202")) {
                    prodProcesso.setCstIcms("102");
                    prodProcesso.setCfop(estpfa.getCodcf1());
                }
            }
        }
    }

    private void processIfNotCompras(ProcessamentoDadosEntrada proBasicRegras, Pedres pedres, ProcessamentoDadosSaida prodProcesso, Estpfa estpfa) {
        if (pedres.getCodtcl() == 3) {
            prodProcesso.setStrAliq(new BigDecimal(0));
            prodProcesso.setStrMva(new BigDecimal(0));
            prodProcesso.setStrBase(new BigDecimal(0));
            prodProcesso.setCfop(estpfa.getCodcf1());
            if (proBasicRegras.getTipEmp() == TipEmpEnum.SIMPLES_NACIONAL) {
                prodProcesso.setCstIcms("40");
            }
        }

                    /*
                      5 = CONSTRUTORA E ENGENHARIA
                     */
        if (pedres.getCodtcl() == 5) {
            prodProcesso.setStrAliq(new BigDecimal(0));
            prodProcesso.setStrMva(new BigDecimal(0));
            prodProcesso.setStrBase(new BigDecimal(0));
            prodProcesso.setIcmsIncFin(new BigDecimal(0));
            prodProcesso.setCfop(estpfa.getCodcf1());
            prodProcesso.setCstIcms(icmsCst);
        }

                    /*
                      CONSUMIDOR FINAL COM I.E
                     */
        if (pedres.getCodtcl() == 4) {
            prodProcesso.setStrMva(new BigDecimal(0));
        }
    }

    private void processConsumidorFinal(ProcessamentoDadosEntrada proBasicRegras, Pedres pedres, ProcessamentoDadosSaida prodProcesso, Estpfa estpfa) {
    /*
      1 = CONSUMIDOR FINAL ISENTO DE I.E
     */
        if (pedres.getCodtcl() == 1) {
            if (proBasicRegras.getSistema().toUpperCase().contains("COMPRAS")) {
                prodProcesso.setStrAliq(new BigDecimal(0));
                prodProcesso.setStrMva(new BigDecimal(0));
                prodProcesso.setStrBase(new BigDecimal(0));
            }

            if (proBasicRegras.getTipEmp() != TipEmpEnum.SIMPLES_NACIONAL) {
                defineCSTAndICMS(proBasicRegras, pedres, prodProcesso, estpfa);
            } else {
                if (prodProcesso.getCstIcms().equalsIgnoreCase("201")) {
                    prodProcesso.setCstIcms("101");
                    prodProcesso.setCfop(estpfa.getCodcf1());
                } else if (prodProcesso.getCstIcms().equalsIgnoreCase("202")) {
                    prodProcesso.setCstIcms("102");
                    prodProcesso.setCfop(estpfa.getCodcf1());
                }
            }
        }
    }

    private void defineCSTAndICMS(ProcessamentoDadosEntrada proBasicRegras, Pedres pedres, ProcessamentoDadosSaida prodProcesso, Estpfa estpfa) {
        if (pedres.getUfecli().equalsIgnoreCase(proBasicRegras.getUfeEmp())) {
            if (prodProcesso.getCstIcms().equalsIgnoreCase("10")
                    || prodProcesso.getCstIcms().equalsIgnoreCase("70")) {
                prodProcesso.setCstIcms(icmsCst);
                prodProcesso.setCfop(estpfa.getCodcf1());
            }
        } else {
            if (prodProcesso.getCstIcms().equalsIgnoreCase("10")
                    || prodProcesso.getCstIcms().equalsIgnoreCase("70")
                    || prodProcesso.getCstIcms().equalsIgnoreCase("60")) {
                prodProcesso.setCstIcms(icmsCst);
                prodProcesso.setCfop(estpfa.getCodcf1());
            }
        }
    }

    private void setMVASN(Pedres pedres, ProcessamentoDadosSaida prodProcesso, BigDecimal strMrgMvaSn, BigDecimal strMrgMvaSnSinief) {
    /*
      LINHAS QUE OPERAM SOBRE O MRGMVA_SN
     */
        if (StringUtils.hasLength(pedres.getRegtrb().toString()) && (pedres.getRegtrb().toString().startsWith("1")
                || (pedres.getRegtrb().toString().startsWith("2")))) {
            switch (prodProcesso.getOrigem().charAt(0)) {
                case '1':
                case '2':
                case '3':
                case '8': {
                    prodProcesso.setStrMva(
                            strMrgMvaSnSinief.doubleValue() > 0 ? strMrgMvaSnSinief : prodProcesso.getStrMva());
                    break;
                }
                default: {
                    prodProcesso.setStrMva(strMrgMvaSn.doubleValue() > 0 ? strMrgMvaSn : prodProcesso.getStrMva());
                }
            }
        }
    }

    private BigDecimal getIcmbase(Esticm esticm) {
        return esticm.getBasicm().compareTo(BigDecimal.valueOf(100)) < 1 ? BigDecimal.valueOf(100) : esticm.getPericm();
    }

    @NotNull
    private Estpfa loadEstPfaInfo(Pedres pedres, ProcessamentoDadosSaida prodProcesso) {
    /*
      Padrao de Faturamento
     */
        Estpfa estpfa = this.estPfaRepository.getByIdentificador(pedres.getTippfa(), pedres.getCodpfa())
                .orElseThrow(() -> new ValidationException(EnumException.ESTPFA_NOT_FOUND));

        prodProcesso.setPfaFrtIcms(estpfa.getFrticm());
        prodProcesso.setPfaSegIcms(estpfa.getSegicm());
        prodProcesso.setPfaOutIcms(estpfa.getDesicm());
        prodProcesso.setPfaDscIcms(estpfa.getDscicm());

        prodProcesso.setPfaFrtSub(estpfa.getFrtsub());
        prodProcesso.setPfaSegSub(estpfa.getSegsub());
        prodProcesso.setPfaOutSub(estpfa.getDessub());
        prodProcesso.setPfaDscSub(estpfa.getDscsub());

        prodProcesso.setPfaFrtIpi(estpfa.getFrtipi());
        prodProcesso.setPfaSegIpi(estpfa.getSegipi());
        prodProcesso.setPfaOutIpi(estpfa.getDesipi());
        prodProcesso.setPfaDscIpi(estpfa.getDscipi());

        if (!StringUtils.hasLength(estpfa.getCodicm())) {
            prodProcesso.setIcmsRegra(estpfa.getCodicm());
            prodProcesso.setIcmsTipo(estpfa.getTipicm());
        }

        if (estpfa.getEstipi() != null && !estpfa.getEstipi().getIdPk().getCodipi().isEmpty()) {
            prodProcesso.setIpiRegra(estpfa.getEstipi().getIdPk().getCodipi());
            prodProcesso.setIcmsTipo(estpfa.getEstipi().getIdPk().getTipipi());
        }
        return estpfa;
    }

    private void loadMainRulesFromTheItem(ProcessamentoDadosEntrada proBasicRegras, Pedres pedres, ProcessamentoDadosSaida prodProcesso) {
    /*
      Carrega as regras do item
     */
        EstProMainInfoInterface mainInfo = this.repository.getRegrasItem(proBasicRegras.getCodClp(),
                proBasicRegras.getCodGru(), proBasicRegras.getCodSub(), proBasicRegras.getCodPro());

        if (mainInfo == null) {
            throw new ValidationException(EnumException.ITEM_NOT_FOUND);
        }

        prodProcesso.setOrigem(mainInfo.getCodst1());
        prodProcesso.setCodUnd(mainInfo.getCoduns());
        prodProcesso.setCbaPro(mainInfo.getCbapro());
        prodProcesso.setDescPro(mainInfo.getDscpro());
        prodProcesso.setQtdEmb(mainInfo.getQtdemb());
        prodProcesso.setProNcm(mainInfo.getCodncm());

        cest = mainInfo.getCest();
        codRegraFcp = mainInfo.getCodFcpSaida();

        /*
          popula se regras de entrada ou saida
         */
        if (pedres.getTippfa().trim().equalsIgnoreCase("Saida")) {
            prodProcesso.setIcmsRegra(mainInfo.getIcmsai());
            prodProcesso.setIcmsTipo(mainInfo.getIcmtsd());
            prodProcesso.setIpiRegra(mainInfo.getIpisai());
            prodProcesso.setIpiTipo(mainInfo.getIpitsd());
            prodProcesso.setStrRegra(mainInfo.getCodsts());
            prodProcesso.setStrTipo(mainInfo.getTipsts());
            idEstIcm = mainInfo.getIdEstIcmSaida();
        } else {

            prodProcesso.setIcmsRegra(mainInfo.getIcment());
            prodProcesso.setIcmsTipo(mainInfo.getIcmten());
            prodProcesso.setIpiRegra(mainInfo.getIpient());
            prodProcesso.setIpiTipo(mainInfo.getIpiten());
            prodProcesso.setStrRegra(mainInfo.getCodste());
            prodProcesso.setStrTipo(mainInfo.getTipste());
            idEstIcm = mainInfo.getIdEstIcmEntrada();
        }
    }

    private void alteraICMSST(ProcessamentoDadosEntrada proBasicRegras, Esticm esticm, Pedres pedres) {
        if (proBasicRegras.getTipEmp() == TipEmpEnum.SIMPLES_NACIONAL && pedres.getCodtcl() == 2) {
            switch (esticm.getCodst2()) {
                case "101":
                case "102":
                case "201":
                case "202": {
                    if (this.aliqCred.doubleValue() > 0) {
                        this.icmsCst = "101";
                    } else {
                        this.icmsCst = "102";
                    }
                    break;
                }
                default:
            }
        }
    }

    @Override
    public Page<Estpro> getAllPaged(Pageable page) {
        return this.repository.findAll(page);
    }

    @Override
    public Estpro getById(Integer id) {
        return null;
    }

    @Override
    public List<IEstProDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }

    @Override
    public Estpro save(Estpro estpro) {
        return this.repository.save(estpro);
    }

    public IEstProDTO getAllDTOByCodclpCodgruCodsubCodpro(String codclp, String codsub, String codgru, String codpro) {
        return this.repository.getProdDTOByCodclpCodgruCodsubCodpro(codclp, codsub, codgru, codpro);
    }
}
