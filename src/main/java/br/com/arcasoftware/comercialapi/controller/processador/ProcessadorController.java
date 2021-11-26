package br.com.arcasoftware.comercialapi.controller.processador;

import br.com.arcasoftware.comercialapi.application.service.est.EstProService;
import br.com.arcasoftware.comercialapi.application.service.fin.FinForService;
import br.com.arcasoftware.comercialapi.application.service.ger.GerEmpService;
import br.com.arcasoftware.comercialapi.application.service.ped.PedResService;
import br.com.arcasoftware.comercialapi.application.service.trigger.pedre2.PedRe2BefUpd00;
import br.com.arcasoftware.comercialapi.application.service.trigger.pedre2.TipEmpEnum;
import br.com.arcasoftware.comercialapi.model.ItemComErro;
import br.com.arcasoftware.comercialapi.model.ProcessamentoDadosEntrada;
import br.com.arcasoftware.comercialapi.model.ProcessamentoDadosSaida;
import br.com.arcasoftware.comercialapi.model.RetornoProcessamento;
import br.com.arcasoftwares.model.Pedre2;
import br.com.arcasoftwares.model.Pedres;
import br.com.arcasoftwares.model.auxiliar.GerEmp;
import br.com.arcasoftwares.model.dto.IEstProDTO;
import br.com.arcasoftwares.model.interfaces.FinForInterface;
import br.com.arcasoftwares.model.primarykey.Pedre2PK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(path = "/processador")
@CrossOrigin("*")
public class ProcessadorController {

	private final EstProService estproService;
	private final FinForService finForService;
	private final PedRe2BefUpd00 pedRe2BefUpd00;
	private final PedResService pedResService;
	private final GerEmpService gerEmpService;

	@Autowired
	public ProcessadorController(EstProService estproService, FinForService finForService, PedRe2BefUpd00 pedRe2BefUpd00,
								 PedResService pedResService,
								 GerEmpService gerEmpService){
		this.estproService = estproService;
		this.finForService = finForService;
		this.pedRe2BefUpd00 = pedRe2BefUpd00;
		this.pedResService = pedResService;
		this.gerEmpService = gerEmpService;
	}

	@GetMapping("modelo")
	public ProcessamentoDadosEntrada getModel(){
		return new ProcessamentoDadosEntrada();
	}

	@PostMapping(value = "processar-items-compra-fornecedor")
	public RetornoProcessamento processarRegrasItensCompra(@RequestBody ProcessamentoDadosEntrada proBasicRegras) {

		long dataInicio = Calendar.getInstance().getTimeInMillis();

		List<FinForInterface> itemsFornecedor = this.finForService.getByCodFor("106");

		RetornoProcessamento retornoProcessamento = new RetornoProcessamento();

		retornoProcessamento.setTotalItems(itemsFornecedor.size());

		Pedres pedRes = this.pedResService.getByNumres(proBasicRegras.getNumres());

		itemsFornecedor.forEach(item -> {
			try {
				proBasicRegras.setCodClp(item.getCodClp());
				proBasicRegras.setCodGru(item.getCodGru());
				proBasicRegras.setCodSub(item.getCodSub());
				proBasicRegras.setCodPro(item.getCodPro());

				ProcessamentoDadosSaida itemProcessado = estproService.processarRegrasItem(proBasicRegras, pedRes);
				retornoProcessamento.getItemsProcessados().add(itemProcessado);
			} catch (Exception e) {
				retornoProcessamento.getListaItemErro().add(new ItemComErro(item.getCodClp(), item.getCodGru(),
						item.getCodSub(), item.getCodPro(), e.getMessage()));
			}
		});

		long dataFim = Calendar.getInstance().getTimeInMillis();

		long tempoDecorrido = dataFim - dataInicio;

		Calendar tempo = Calendar.getInstance();
		tempo.set(Calendar.HOUR_OF_DAY, 0);
		tempo.set(Calendar.MINUTE, 0);
		tempo.set(Calendar.SECOND, 0);
		tempo.set(Calendar.MILLISECOND, 0);
		tempo.add(Calendar.MILLISECOND, (int) tempoDecorrido);

		retornoProcessamento.setTempoGasto(new SimpleDateFormat("HH:mm:ss.SSSS").format(tempo.getTime()));

		return retornoProcessamento;
	}

	@PostMapping(value = "processar-regras-item")
	public Object processarRegrasItem(@RequestBody ProcessamentoDadosEntrada proBasicRegras) {

		RetornoProcessamento retornoProcessamento = new RetornoProcessamento();
		Pedres pedRes = this.pedResService.getByNumres(proBasicRegras.getNumres());

		proBasicRegras.setSistema("ECOMERCIAL");

		// Populate info with data from database
		GerEmp gerEmp = this.gerEmpService.getAllShort().get(0);
		proBasicRegras.setUfeEmp(gerEmp.getSigufe());
		proBasicRegras.setTipEmp(TipEmpEnum.getEnumFromDescription(gerEmp.getTipemp()));

		try {
			ProcessamentoDadosSaida itemProcessado = estproService.processarRegrasItem(proBasicRegras, pedRes);
			retornoProcessamento.getItemsProcessados().add(itemProcessado);
			return this.createPedResItem(itemProcessado, pedRes);
		} catch (Exception e) {
			retornoProcessamento.getListaItemErro()
					.add(new ItemComErro(proBasicRegras.getCodClp(), proBasicRegras.getCodGru(),
							proBasicRegras.getCodSub(), proBasicRegras.getCodPro(), e.getMessage()));
		}

		return retornoProcessamento;
	}

	private Pedre2 createPedResItem(ProcessamentoDadosSaida itemProcessado, Pedres pedres) throws ValidationException {
		Pedre2 re2 = new Pedre2();
		Pedre2PK pk = new Pedre2PK();

		pk.setCodemp(pedres.getId().getCodemp());
		pk.setNumres(pedres.getId().getNumres());
		pk.setDteres(pedres.getId().getDteres());

		re2.setId(pk);

		re2.setCodclp(itemProcessado.getCodClp());
		re2.setCodgru(itemProcessado.getCodGru());
		re2.setCodsub(itemProcessado.getCodSub());
		re2.setCodpro(itemProcessado.getCodPro());

		re2.setDsrre2(BigDecimal.ZERO);
		re2.setDscre2(BigDecimal.ZERO);
		re2.setCodcom("");

		final IEstProDTO prodDTO = this.estproService.getAllDTOByCodclpCodgruCodsubCodpro(itemProcessado.getCodClp(),
				itemProcessado.getCodSub(),
				itemProcessado.getCodGru(),
				itemProcessado.getCodPro());

		re2.setDesre2(prodDTO.getDscpro());

		re2.setQtpre2(BigDecimal.TEN);
		re2.setVlure2(BigDecimal.valueOf(130));
		re2.setTabprc(1);
		re2.setTotfrt(BigDecimal.ZERO);
		re2.setFrticm(BigDecimal.ZERO);
		re2.setTotoutdesp(BigDecimal.ZERO);
		re2.setTotseg(BigDecimal.ZERO);
		re2.setTotdescinc(BigDecimal.ZERO);
		re2.setDesicm(BigDecimal.ZERO);
		re2.setSegicm(BigDecimal.ZERO);
		re2.setDscicm(BigDecimal.ZERO);
		re2.setRedicm(BigDecimal.ZERO);

		re2.setPcoatd(BigDecimal.ZERO);
		re2.setAliqIbpt(BigDecimal.ZERO);

		re2.setCodst1(itemProcessado.getOrigem());

		re2.setBasesb(itemProcessado.getStrBase());

		re2.setFlgfec("Nao");

		re2.setCodund(itemProcessado.getCodUnd());
		re2.setQtdemb(itemProcessado.getQtdEmb());

		re2.setRegipi(itemProcessado.getIpiRegra());
		re2.setTipipi(itemProcessado.getIpiTipo());
		re2.setIpire2(itemProcessado.getIpiAliq());
		re2.setBscipi(itemProcessado.getIpiBase());
		re2.setCstipi(itemProcessado.getIpiCst());

		re2.setRegicm(itemProcessado.getIcmsRegra());
		re2.setTipicm(itemProcessado.getIcmsTipo());
		re2.setIcmre2(itemProcessado.getIcmsAliq());
		re2.setCodst2(itemProcessado.getCstIcms());
		re2.setBscicm(itemProcessado.getIcmsBase());

		re2.setFlgDescZfPis(itemProcessado.getPisFlgDescZf());
		re2.setAliqpis(itemProcessado.getPisAliq());
		re2.setCstpis(itemProcessado.getPisCst());

		re2.setFlgDescZfCof(itemProcessado.getCofFlgDescZf());
		re2.setAliqcof(itemProcessado.getCofAliq());
		re2.setCstcof(itemProcessado.getCofCst());

		re2.setQtfre2(BigDecimal.ZERO);
		re2.setQtdfab(BigDecimal.ZERO);
		re2.setDspre2(BigDecimal.ZERO);
		re2.setVcsre2(BigDecimal.valueOf(100));

		re2.setLiqre2(BigDecimal.ZERO);
		re2.setBrtre2(BigDecimal.ZERO);
		re2.setQtsre2(BigDecimal.ZERO);
		re2.setSldre2(BigDecimal.ZERO);
		re2.setVlqre2(BigDecimal.ZERO);
		re2.setDscre2(BigDecimal.ZERO);
		re2.setVdsre2(BigDecimal.ZERO);
		re2.setDspre2(BigDecimal.ZERO);
		re2.setVdpre2(BigDecimal.ZERO);
		re2.setPacre2(BigDecimal.ZERO);
		re2.setVacre2(BigDecimal.ZERO);
		re2.setPcore2(BigDecimal.ZERO);
		re2.setTotven(BigDecimal.ZERO);
		re2.setTotcst(BigDecimal.ZERO);
		re2.setTotren(BigDecimal.ZERO);
		re2.setBasipi(BigDecimal.ZERO);
		re2.setTotipi(BigDecimal.ZERO);
		re2.setToticm(BigDecimal.ZERO);
		re2.setTotre2(BigDecimal.ZERO);
		re2.setTotge2(BigDecimal.ZERO);
		re2.setVolre2(BigDecimal.ZERO);
		re2.setTotbrt(BigDecimal.ZERO);
		re2.setBascom(BigDecimal.ZERO);
		re2.setTotcom(BigDecimal.ZERO);
		re2.setTotliq(BigDecimal.ZERO);
		re2.setDsccom(BigDecimal.ZERO);
		re2.setVdscom(BigDecimal.ZERO);
		re2.setTotdco(BigDecimal.ZERO);
		re2.setFrtsub(BigDecimal.ZERO);
		re2.setSegsub(BigDecimal.ZERO);
		re2.setDessub(BigDecimal.ZERO);
		re2.setDscsub(BigDecimal.ZERO);

		re2.setRedsub(null != itemProcessado.getStrRed() ? itemProcessado.getStrRed() : BigDecimal.ZERO);
		re2.setMrgsub(null != itemProcessado.getStrMva() ? itemProcessado.getStrMva() : BigDecimal.ZERO);
		re2.setIcmsub(null != itemProcessado.getIcmsAliq() ? itemProcessado.getStrAliq() : BigDecimal.ZERO);

		re2.setFlaseq("");
		re2.setCodclp("1");
		re2.setCodtam("UN");
		re2.setCodcor("UN");
		re2.setFlgres("Nao");
		re2.setFlgreq("Nao");
		re2.setFlgdup("Nao");
		return pedRe2BefUpd00.processar(re2);
	}
}
