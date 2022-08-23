package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FinregtribRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.Finregtrib;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinregtribService {


    private final FinregtribRepository repository;

    public FinregtribService(FinregtribRepository repository) {
        this.repository = repository;
    }

    public Optional<Finregtrib> getByCnpjEmpresaAndNumregtrib(String cnpjEmpresa, Integer numregtrib) {
        return this.repository.findByCnpjEmpresaAndNumregtrib(cnpjEmpresa, numregtrib);

    }

    public Finregtrib save(Finregtrib entity) {
        return this.repository.save(entity);
    }

}
