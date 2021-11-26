package br.com.arcasoftware.comercialapi.application.repository.est;

import br.com.arcasoftwares.model.Estpfa;
import br.com.arcasoftwares.model.dto.IEstPfaDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface EstPfaRepository extends PagingAndSortingRepository<Estpfa, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT pfa.codpfa, pfa.dscpfa, pfa.tippfa, pfa.locpfa, pfa.codcf1, pfa.ALIQPIS, pfa.ALIQCOF, pfa.CSTPIS,pfa.CSTCOF FROM estpfa pfa ")
    List<IEstPfaDTO> getAllDTO();

    @Cacheable(value = "estpfa_identificador")
    @Query(nativeQuery = true,
            value = "select * from estpfa pfa where (pfa.tippfa) = ?1 and (pfa.codpfa) = ?2")
    Optional<Estpfa> getByIdentificador(String tippfa, String codpfa);
}
