package br.com.arcasoftware.comercialapi.application.repository;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerDataRepository extends JpaRepository<CustomerData, UUID> {
}
