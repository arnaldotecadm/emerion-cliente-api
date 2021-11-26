package br.com.arcasoftware.comercialapi.application.repository.fin;

import br.com.arcasoftwares.model.Finpai;
import br.com.arcasoftwares.model.dto.FinPaiDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FinPaiRepository extends CrudRepository<Finpai, Integer> {

	@Query(value = "select new br.com.arcasoftwares.model.dto.FinPaiDTO(idFinpai, nompai) from Finpai")
	List<FinPaiDTO> getAllShort();
}