package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.TipIndicIe;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TipIndicIeRepository extends PagingAndSortingRepository<TipIndicIe, Long> {

    Optional<TipIndicIe> findByCnpjEmpresaAndId(String cnpjEmpresa, Integer id);
}
