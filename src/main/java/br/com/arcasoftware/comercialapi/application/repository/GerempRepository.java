package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.Geremp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GerempRepository extends CrudRepository<Geremp, Long> {

    Optional<Geremp> findByCnpjEmpresaAndCodemp(String cnpjEmpresa, long codemp);
}
