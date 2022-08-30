package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.EstproRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.Estpro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstproService {

    private final EstproRepository repository;

    @Autowired
    public EstproService(EstproRepository repository) {
        this.repository = repository;
    }

    public Estpro save(Estpro data) {
        return this.repository.save(data);
    }
}
