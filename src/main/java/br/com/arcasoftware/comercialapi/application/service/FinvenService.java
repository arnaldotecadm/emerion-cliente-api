package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FinvenRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.Finven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinvenService {

    private final FinvenRepository repository;

    @Autowired
    public FinvenService(FinvenRepository repository) {
        this.repository = repository;
    }

    public void save(Finven data) {
        Optional<Finven> fromDataBase = this.repository.findByCnpjEmpresaAndCodven(data.getCnpjEmpresa(), data
                .getCodven());

        fromDataBase.ifPresent((item) -> {
            data.setId(item.getId());
        });

        this.repository.save(data);
    }
}
