package br.com.arcasoftware.comercialapi.application.repository.fin;

import br.com.arcasoftwares.model.Finven;
import br.com.arcasoftwares.model.dto.IFinVenDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FinVenRepository extends PagingAndSortingRepository<Finven, Integer> {

    @Query(nativeQuery = true, value = "select codven, nomven, apeven, cgcven, fonven  from Finven")
    List<IFinVenDTO> getAllDTO();

}
