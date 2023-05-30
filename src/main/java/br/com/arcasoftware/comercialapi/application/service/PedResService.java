package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.PedResRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.Pedres;
import br.com.arcasoftware.comercialapi.model.IReportPedRe2Detail;
import br.com.arcasoftware.comercialapi.model.IReportPedResHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedResService {

    private final PedResRepository repository;

    @Autowired
    public PedResService(PedResRepository repository) {
        this.repository = repository;
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

}
