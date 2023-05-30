package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID> {

    List<CustomerAddress> findByCnpjEmpresaAndCodcli(String cnpjEmpresa, String codcli);
}
