package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.PedRe2Repository;
import br.com.arcasoftware.comercialapi.application.repository.model.Pedre2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pedre2Service {

    private final PedRe2Repository repository;

    @Autowired
    public Pedre2Service(PedRe2Repository repository) {
        this.repository = repository;
    }

    public Pedre2 save(Pedre2 data) {
        return this.repository.save(data);
    }
}
