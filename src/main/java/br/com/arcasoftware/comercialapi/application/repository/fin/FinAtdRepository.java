package br.com.arcasoftware.comercialapi.application.repository.fin;

import br.com.arcasoftwares.model.Finatd;
import br.com.arcasoftwares.model.dto.FinAtdDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FinAtdRepository extends CrudRepository<Finatd, Integer> {

	@Query(value = "select new br.com.arcasoftwares.model.dto.FinAtdDTO(codatd, nomatd, apeatd) from Finatd")
	List<FinAtdDTO> getAllShort();
}