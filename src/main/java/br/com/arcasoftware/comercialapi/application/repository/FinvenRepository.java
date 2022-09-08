package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.Finven;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinvenRepository extends CrudRepository<Finven, Long> {

    Optional<Finven> findByCnpjEmpresaAndCodven(String cnpjEmpresa, long codven);
}
