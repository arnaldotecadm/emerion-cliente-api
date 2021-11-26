package br.com.arcasoftware.comercialapi.application.repository.fin;

import br.com.arcasoftwares.model.Fincli;
import br.com.arcasoftwares.model.dto.IFinCliDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FinCliRepository extends PagingAndSortingRepository<Fincli, Integer> {

    @Query(nativeQuery = true, value = "select c.codcli, c.nomcli, c.cgccli, c.cifcli, c.uffcli, c.codven, c.cnae  from Fincli c ")
    List<IFinCliDTO> getAllDTO();

    @Query(value = "select c from Fincli c where 1 = 1 and (:codcli is null or c.codcli = :codcli) and (:nomcli is null or c.nomcli like %:nomcli%)")
    List<Fincli> getByCodcliAndNomcli(Integer codcli, String nomcli);
}
