package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.Fintra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FintraRepository extends CrudRepository<Fintra, Long> {

    Optional<Fintra> findByCnpjEmpresaAndCodtra(String cnpjEmpresa, long codtra);
}
