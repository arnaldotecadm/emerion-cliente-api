package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FintraRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.Fintra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FintraService {

    private final FintraRepository repository;

    @Autowired
    public FintraService(FintraRepository repository) {
        this.repository = repository;
    }

    public void save(Fintra data) {
        Optional<Fintra> fromDataBase = this.repository.findByCnpjEmpresaAndCodtra(data.getCnpjEmpresa(), data
                .getCodtra());

        fromDataBase.ifPresent((item) -> {
            data.setId(item.getId());
        });

        this.repository.save(data);
    }
}
