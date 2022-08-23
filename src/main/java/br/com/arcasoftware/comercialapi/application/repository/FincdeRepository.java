package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.Fincde;
import br.com.arcasoftware.comercialapi.application.repository.model.Finregtrib;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface FincdeRepository extends PagingAndSortingRepository<Fincde, Long> {

    Optional<Finregtrib> findByCnpjEmpresaAndCodempAndDtecdeAndSeqcde(String cnpjEmpresa, int codemp, String dtecde, int sqecde);
}
