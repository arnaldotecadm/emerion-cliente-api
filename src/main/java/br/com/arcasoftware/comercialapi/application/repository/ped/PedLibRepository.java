package br.com.arcasoftware.comercialapi.application.repository.ped;

import br.com.arcasoftwares.model.Pedlib;
import br.com.arcasoftwares.model.primarykey.PedlibPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedLibRepository extends CrudRepository<Pedlib, PedlibPK> {

	@Query(value = "select p from Pedlib p where p.id.numres = :numres")
    Pedlib getByNumres(Integer numres);

	@Query(value = "select p from Pedlib p where 1 = 1  " + " and (:sitlib is null or p.sitlib like %:sitlib%)"
			+ " and (:numres is null or p.id.numres = :numres)")
    List<Pedlib> findByFilters(Integer numres, String sitlib);
}
