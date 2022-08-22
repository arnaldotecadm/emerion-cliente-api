package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.PedResRepository;
import br.com.arcasoftware.comercialapi.model.IPedRe2DTO;
import br.com.arcasoftware.comercialapi.model.IPedResCab;
import br.com.arcasoftware.comercialapi.model.IPedResDTO;
import br.com.arcasoftware.comercialapi.model.IReportPedRe2Detail;
import br.com.arcasoftware.comercialapi.model.IReportPedResHead;
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

    public IPedResCab getCabecalhoPedido(int numres) {
        return repository.getCabecalhoPedido(numres);
    }

    public List<IPedResDTO> getCabecalhoPedidoListByCodcli(Integer codcli) {
        return repository.getCabecalhoPedidoListByCodcli(codcli);
    }

    public byte[] getNfePedido(Integer numres) {
        return this.repository.getNfePedido(numres);
    }

    public IReportPedResHead getReportPedRes(Integer numres) {
        return this.repository.getReportPedRes(numres);
    }

    public List<IReportPedRe2Detail> getReportPedRe2(Integer codemp, Date dteres, Integer numres) {
        return this.repository.getReportPedRe2(codemp, dteres, numres);
    }

    public List<IPedRe2DTO> getDetalhesPedido(int numres) {
        return repository.getDetalhesPedido(numres);
    }

}
