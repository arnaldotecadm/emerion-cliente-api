package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.CustomerOrderRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerData;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    public CustomerOrder save(CustomerOrder customerOrder) {
        Optional<CustomerOrder> customerDataDb = this.getAllByCnpjEmpresaAndNumres(customerOrder.getCnpjEmpresa(), customerOrder.getNumres());
        customerDataDb.ifPresent(data -> customerOrder.setId(data.getId()));
        return this.customerOrderRepository.save(customerOrder);
    }

    public List<CustomerOrder> getAll() {
        return this.customerOrderRepository.findAll();
    }

    public CustomerOrder getById(UUID customerOrderId) {
        return this.customerOrderRepository.findById(customerOrderId).orElseThrow(() -> new ValidationException(ValidationEnum.ORDER_NOT_FOUND));
    }

    public List<CustomerOrder> getAllByCnpjEmpresaAndCodcli(String cnpjEmprsa, String codcli) {
        return this.customerOrderRepository.findByCnpjEmpresaAndCodcli(cnpjEmprsa, codcli);
    }

    public Optional<CustomerOrder> getAllByCnpjEmpresaAndNumres(String cnpjEmpresa, String numres) {
        return this.customerOrderRepository.findByCnpjEmpresaAndNumres(cnpjEmpresa, numres);
    }
}
