package br.com.arcasoftware.comercialapi.application.repository.ger;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.arcasoftwares.model.GerPro;

public interface GerProRepository extends CrudRepository<GerPro, Integer> {

	@Cacheable(value = "gerpro_identificador")
	@Query(nativeQuery = true, value = "SELECT REGICM,TIPICM from GERPRO " + "  where SIGUFE = :sigUfe "
			+ "    and CodClp = :codClp " + "    and CodGru = :codGru " + "    and CodSub = :codSub"
			+ "    and CodPro = :codPro")
	List<GerPro> getByIdentificador(@Param("sigUfe") String sigUfe, @Param("codClp") String codClp,
			@Param("codGru") String codGru, @Param("codSub") String codSub, @Param("codPro") String codPro);
}