package br.com.arcasoftware.comercialapi.application.repository.est;

import br.com.arcasoftwares.model.Eststr;
import br.com.arcasoftwares.model.dto.IEstStrDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EstStrRepository extends PagingAndSortingRepository<Eststr, Integer> {

    @Query(nativeQuery = true, value = "SELECT str.codstr, str.nomstr, str.tipstr, str.ncm, str.ivaent, str.icmsent FROM eststr str")
    List<IEstStrDTO> getAllDTO();
}
