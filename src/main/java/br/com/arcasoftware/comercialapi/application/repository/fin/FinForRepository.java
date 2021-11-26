package br.com.arcasoftware.comercialapi.application.repository.fin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.arcasoftwares.model.Finfor;
import br.com.arcasoftwares.model.interfaces.FinForInterface;

public interface FinForRepository extends CrudRepository<Finfor, Integer> {

	@Query(nativeQuery = true, value = "select pro.codclp, pro.codgru, pro.codsub, pro.codpro, valpfo"
			+ "       from cmppfo pfo " + "       join estpro pro on " + "       pfo.codclp = pro.codclp "
			+ "       and pfo.codgru = pro.codgru " + "       and pfo.codsub = pro.codsub "
			+ "       and pfo.codpro = pro.codpro " + "       where pfo.CODFOR = :codFor"
			+ "       and coalesce(pro.flbpro,'') <> '*'" + "       and coalesce(valpfo,0) > 0 "
			+ "       order by pfo.codclp, pfo.codgru, pfo.codsub, pfo.codpro")
	List<FinForInterface> getByCodFor(@Param("codFor") String codFor);
}