package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerCreditRepository extends JpaRepository<CustomerCredit, UUID> {

    List<CustomerCredit> findByCnpjEmpresaAndCodcli(String cnpjEmpresa, String codcli);
}
