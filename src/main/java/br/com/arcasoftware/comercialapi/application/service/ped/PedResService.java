package br.com.arcasoftware.comercialapi.application.service.ped;

import br.com.arcasoftware.comercialapi.application.repository.ped.PedResRepository;
import br.com.arcasoftwares.model.Pedres;
import br.com.arcasoftwares.model.dto.IPedRe2DTO;
import br.com.arcasoftwares.model.dto.IPedResCab;
import br.com.arcasoftwares.model.dto.IPedResDTO;
import br.com.arcasoftwares.model.dto.PedResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<IPedRe2DTO> getDetalhesPedido(String numres) {
        return repository.getDetalhesPedido(numres);
    }

    public IPedResCab getCabecalhoPedido(String numres) {
        return repository.getCabecalhoPedido(numres);
    }

    public List<IPedResDTO> getCabecalhoPedidoListByCodcli(Integer codcli) {
        return repository.getCabecalhoPedidoListByCodcli(codcli);
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

}
