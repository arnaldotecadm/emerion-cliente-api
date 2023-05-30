package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, UUID> {

    List<CustomerOrder> findByCnpjEmpresaAndCodcli(String cnpjEmpresa, String codcli);
    Optional<CustomerOrder> findByCnpjEmpresaAndNumres(String cnpjEmpresa, String numres);
}
