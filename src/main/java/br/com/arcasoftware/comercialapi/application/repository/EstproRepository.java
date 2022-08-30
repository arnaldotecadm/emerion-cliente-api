package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.Estpro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstproRepository extends CrudRepository<Estpro, Long> {

}
