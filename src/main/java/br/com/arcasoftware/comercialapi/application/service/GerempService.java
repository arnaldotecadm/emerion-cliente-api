package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.GerempRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.Geremp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerempService {

    private final GerempRepository repository;

    @Autowired
    public GerempService(GerempRepository repository) {
        this.repository = repository;
    }

    public void save(Geremp data) {
        Optional<Geremp> fromDataBase = this.repository.findByCnpjEmpresaAndCodemp(data.getCnpjEmpresa(), data
                .getCodemp());

        fromDataBase.ifPresent((item) -> {
            data.setId(item.getId());
        });

        this.repository.save(data);
    }
}
