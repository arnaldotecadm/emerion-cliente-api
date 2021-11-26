package br.com.arcasoftware.comercialapi.application.repository.est;

import br.com.arcasoftwares.model.Estipi;
import br.com.arcasoftwares.model.dto.IEstIpiDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface EstIpiRepository extends PagingAndSortingRepository<Estipi, Integer> {

    @Query(nativeQuery = true, value = "SELECT ipi.codipi, ipi.tipipi, ipi.nomipi, ipi.id_estncm as idestncm, ipi.cstpis, ipi.cstcof, ipi.ALIQ_PIS as ALIQPIS," +
            " ipi.ALIQ_COF as ALIQCOF, ipi.cstipi,\n" +
            "\tipi.peripi, ipi.cod_enq, ipi.ID_ESTSIP as IDESTSIP, ipi.ID_ESTIPI as IDESTIPI \n" +
            "FROM estipi ipi")
    List<IEstIpiDTO> getAllDTO();

    @Cacheable(value = "estipi_identificador")
    @Query(nativeQuery = true, value = "select * from estipi ipi where ipi.tipipi = ?1 and ipi.codipi = ?2")
    Optional<Estipi> getByIdentificador(String tipipi, String ipi);
}
