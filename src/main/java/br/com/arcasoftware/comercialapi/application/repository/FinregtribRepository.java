package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.Finregtrib;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface FinregtribRepository extends PagingAndSortingRepository<Finregtrib, Long> {

    Optional<Finregtrib> findByCnpjEmpresaAndNumregtrib(String cnpjEmpresa, Integer numregtrib);
}
