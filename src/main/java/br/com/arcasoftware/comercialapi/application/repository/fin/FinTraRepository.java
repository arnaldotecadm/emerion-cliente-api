package br.com.arcasoftware.comercialapi.application.repository.fin;

import br.com.arcasoftwares.model.Fintra;
import br.com.arcasoftwares.model.dto.IFinTraDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FinTraRepository extends PagingAndSortingRepository<Fintra, Integer> {

    @Query(nativeQuery = true, value = "select t.codtra, t.nomtra, t.apetra, t.cgctra, t.fontra from Fintra t ")
    List<IFinTraDTO> getAllDTO();

}
