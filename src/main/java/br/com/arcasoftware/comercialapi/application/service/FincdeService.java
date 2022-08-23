package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FincdeRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.Fincde;
import br.com.arcasoftware.comercialapi.application.repository.model.Finregtrib;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FincdeService {


    private final FincdeRepository repository;

    public FincdeService(FincdeRepository repository) {
        this.repository = repository;
    }

    public Optional<Finregtrib> getByCnpjEmpresaAndCodempAndDtecdeAndSeqcde(String cnpjEmpresa, int codemp, String dtecde, int seqcde) {
        return this.repository.findByCnpjEmpresaAndCodempAndDtecdeAndSeqcde(cnpjEmpresa, codemp, dtecde, seqcde);

    }

    public Fincde save(Fincde entity) {
        return this.repository.save(entity);
    }

}
