package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.CustomerOrderDetailRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerOrderDetailService {

    private final CustomerOrderDetailRepository customerOrderDetailRepository;

    public CustomerOrderDetailService(CustomerOrderDetailRepository customerOrderDetailRepository) {
        this.customerOrderDetailRepository = customerOrderDetailRepository;
    }

    public CustomerOrderDetail save(CustomerOrderDetail customerOrderDetail) {
        return this.customerOrderDetailRepository.save(customerOrderDetail);
    }

    public List<CustomerOrderDetail> getAll() {
        return this.customerOrderDetailRepository.findAll();
    }

    public List<CustomerOrderDetail> getAllByCnpjEmpresaAndCodcli(String cnpjEmprsa, String codcli) {
        return this.customerOrderDetailRepository.findByCnpjEmpresaAndCodcli(cnpjEmprsa, codcli);
    }

    public List<CustomerOrderDetail> findByCustomerOrder(UUID customerOrder) {
        return this.customerOrderDetailRepository.findByCustomerOrder(customerOrder);
    }
}
