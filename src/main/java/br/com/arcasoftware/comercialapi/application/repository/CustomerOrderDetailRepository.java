package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerOrderDetailRepository extends JpaRepository<CustomerOrderDetail, UUID> {

    List<CustomerOrderDetail> findByCnpjEmpresaAndCodcli(String cnpjEmpresa, String codcli);

    List<CustomerOrderDetail> findByCustomerOrder(UUID customerOrder);
}
