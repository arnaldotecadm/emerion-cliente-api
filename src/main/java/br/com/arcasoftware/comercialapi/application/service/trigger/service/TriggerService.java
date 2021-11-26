package br.com.arcasoftware.comercialapi.application.service.trigger.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arcasoftware.comercialapi.application.service.trigger.model.EstIteInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.FlgConInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.QtdPedRe2Interface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.RegrasPadraoFatInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.RegrasProdutoInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.model.SitPedInterface;
import br.com.arcasoftware.comercialapi.application.service.trigger.repository.GerParRepository;

@Service
public class TriggerService {

	@Autowired
	private GerParRepository repository;

	public FlgConInterface getFlgCon() {
		return repository.getFlgCon();
	}

	public SitPedInterface getSitLojPed(Integer numPed) {
		return repository.getSitLojPed(numPed);
	}

	public SitPedInterface getSitPedRes(Integer numPed) {
		return repository.getSitPedRes(numPed);
	}

	public Integer getTotalItens(Integer codemp, Date dteres, Integer numres, String codclp, String codgru,
			String codsub, String codpro) {
		return this.repository.getTotalItens(codemp, dteres, numres, codclp, codgru, codsub, codpro);
	}

	public Integer getTotalItensISSR2(Integer codemp, Date dteres, Integer numres) {
		return this.repository.getTotalItensISSR2(codemp, dteres, numres);
	}

	public String getCodBarByProduto(String codclp, String codgru, String codsub, String codpro) {
		List<String> barList = this.repository.getCodBarByProduto(codclp, codgru, codsub, codpro);

		return barList != null ? barList.get(0) : null;
	}

	public String getTipEmp(Integer codemp) {
		return this.repository.getTipEmp(codemp);
	}

	public String getTipPedFromParametro() {
		return this.repository.getTipPedFromParametro();
	}

	public BigDecimal getAcrMaxFromParametro() {
		return this.repository.getAcrMaxFromParametro();
	}

	public RegrasPadraoFatInterface getRegrasPadraoFatByPedido(Integer codemp, Date dteres, Integer numres) {
		return this.repository.getRegrasPadraoFatByPedido(codemp, dteres, numres);
	}

	public Integer getMaxSeqRe2(Integer codemp, Date dteres, Integer numres) {
		return this.repository.getMaxSeqRe2(codemp, dteres, numres);
	}

	public RegrasProdutoInterface getRegrasProdutoByIdentificacao(String codclp, String codgru, String codsub,
			String codpro) {
		return this.repository.getRegrasProdutoByIdentificacao(codclp, codgru, codsub, codpro);
	}

	public EstIteInterface getestIteByIdentificacao(Integer codemp, String codclp, String codgru, String codsub,
			String codpro) {
		return this.repository.getestIteByIdentificacao(codemp, codclp, codgru, codsub, codpro);
	}

	public BigDecimal getPerCom(String codcom) {
		return this.repository.getPerCom(codcom);
	}

	public Integer getCodPrm(Date dteres) {
		return this.repository.getCodPrm(dteres);
	}

	public QtdPedRe2Interface getQtdPedRe2ByIdentificacaoItem(Integer codprm, String codclp, String codgru,
			String codsub, String codpro) {
		return this.repository.getQtdPedRe2ByIdentificacaoItem(codprm, codclp, codgru, codsub, codpro);
	}

	public BigDecimal getQtdPed(Integer codprm, Integer seqpr2, Integer tabprc) {
		return this.repository.getQtdPed(codprm, seqpr2, tabprc);
	}
}
