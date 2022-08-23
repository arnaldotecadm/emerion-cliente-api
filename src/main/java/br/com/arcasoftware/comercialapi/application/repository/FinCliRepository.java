package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.ClienteDocument;
import br.com.arcasoftware.comercialapi.model.dto.IFinCliDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface FinCliRepository extends PagingAndSortingRepository<ClienteDocument, Long> {

    @Query(nativeQuery = true, value = "select c.codcli, c.nomcli, c.cgccli, c.cifcli, c.uffcli, c.codven, c.cnae  from Fincli c order by c.codcli")
    List<IFinCliDTO> getAllDTO();

    Optional<ClienteDocument> findByCnpjEmpresaAndCodcli(String cnpjEmpresa, long codcli);
}
