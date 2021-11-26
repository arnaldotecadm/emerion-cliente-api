package br.com.arcasoftware.comercialapi.application.service.ped;

import br.com.arcasoftware.comercialapi.application.repository.ped.PedResRepository;
import br.com.arcasoftwares.model.Pedres;
import br.com.arcasoftwares.model.dto.*;
import br.com.arcasoftwares.model.interfaces.ResumoPorSituacaoInterface;
import br.com.emerion.model.generics.SitResEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PedResService {

    private final PedResRepository repository;

    @Autowired
    public PedResService(PedResRepository repository) {
        this.repository = repository;
    }

    public List<IPedResDTO> getCabecalhoPedidoList(Integer recordCount) {
        return repository.getCabecalhoPedidoList(recordCount);
    }

    public IPedResInfoDTO getPedInfo(Integer numres) {
        return this.repository.getPedInfo(numres);
    }

    public List<IPedRe2DTO> getDetalhesPedido(String numres) {
        return repository.getDetalhesPedido(numres);
    }

    public IPedResCab getCabecalhoPedido(String numres) {
        return repository.getCabecalhoPedido(numres);
    }

    public List<IPedResDTO> getCabecalhoPedidoListByCodcli(Integer codcli) {
        return repository.getCabecalhoPedidoListByCodcli(codcli);
    }

    public List<IPedResDTO> getCabecalhoPedidoListBySitres(SitResEnum sitres) {
        return repository.getCabecalhoPedidoListBySitres(sitres.getLabel());
    }

    public List<Pedres> getAll() {
        return repository.getAll();
    }

    public Pedres getByNumres(Integer numres) {
        return repository.getByNumres(numres);
    }

    public List<Pedres> getBySitres(String sitres) {
        return this.repository.findBySitres(sitres);
    }

    public List<Pedres> getByCodcli(Integer codcli) {
        return this.repository.findByCodcli(codcli);
    }

    public List<ResumoPorSituacaoInterface> getResumoPorSituacao() {
        return this.repository.getResumoPorSituacao();
    }

    public void updateStatus(Integer numres, String sitres) {
        repository.updateStatus(numres, sitres);
    }

    public void rejeitarPedido(Integer numres, String sitres, String obsrej) {
        repository.rejeitarPedido(numres, sitres, obsrej);
    }

    public void updateStatusPedidoSemObservacao(Integer numres, String sitres) {
        repository.updateStatusPedidoSemObservacao(numres, sitres);
    }

    public void aprovarPeriodoProgramacao(Integer numres, String sitres, String obsrej) {
        repository.aprovarPeriodoProgramacao(numres, sitres, obsrej);
    }

    public void aprovarLiberacaoDptoComercial(Integer numres, String sitres, String obs) {
        repository.aprovarLiberacaoDptoComercial(numres, sitres, obs);
    }

    public void aprovarLiberacaoConsultaCadastro(Integer numres, String sitres, String obs) {
        repository.aprovarLiberacaoConsultaCadastro(numres, sitres, obs);
    }

    public void aprovarLiberacaoDptoFinanceiro(Integer numres, String sitres, String obs) {
        repository.aprovarLiberacaoDptoFinanceiro(numres, sitres, obs);
    }

    public void aprovarConfirmacaoPagamento(Integer numres, String sitres, String obs) {
        repository.aprovarConfirmacaoPagamento(numres, sitres, obs);
    }

    public byte[] getNfePedido(Integer numres) {
        return this.repository.getNfePedido(numres);
    }

    public List<Pedres> getByFilter(PedResDTO pedResDTO) {
        return repository.findByFilters(
                pedResDTO.getFinCliDTO() != null ? pedResDTO.getFinCliDTO().getCodcli() : null,
                (pedResDTO.getFinCliDTO() != null && pedResDTO.getFinCliDTO().getNomcli() != null) ? pedResDTO.getFinCliDTO().getNomcli().toUpperCase() : null,

                pedResDTO.getNumres(),
                pedResDTO.getSitres());
    }

    public IReportPedResHead getReportPedRes(Integer numres) {
        return this.repository.getReportPedRes(numres);
    }

    public List<IReportPedRe2Detail> getReportPedRe2(Integer codemp, Date dteres, Integer numres) {
        return this.repository.getReportPedRe2(codemp, dteres, numres);
    }
}
