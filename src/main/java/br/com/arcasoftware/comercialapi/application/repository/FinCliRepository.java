package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftwares.model.Fincli;
import br.com.arcasoftwares.model.dto.IFinCliDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FinCliRepository extends PagingAndSortingRepository<Fincli, Integer> {

    @Query(nativeQuery = true, value = "select c.codcli, c.nomcli, c.cgccli, c.cifcli, c.uffcli, c.codven, c.cnae  from Fincli c ")
    List<IFinCliDTO> getAllDTO();

}
