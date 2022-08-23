package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.TipIndicIeRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.TipIndicIe;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipIndicIeService {


    private final TipIndicIeRepository repository;

    public TipIndicIeService(TipIndicIeRepository repository) {
        this.repository = repository;
    }

    public Optional<TipIndicIe> getByCnpjEmpresaAndId(String cnpjEmpresa, Integer id) {
        return this.repository.findByCnpjEmpresaAndId(cnpjEmpresa, id);

    }

    public TipIndicIe save(TipIndicIe entity) {
        return this.repository.save(entity);
    }

}
