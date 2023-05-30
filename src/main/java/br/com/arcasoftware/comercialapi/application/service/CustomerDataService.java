package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.CustomerDataRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerData;
import br.com.arcasoftware.comercialapi.mapper.CustomerDataMapper;
import br.com.arcasoftware.customerapi.model.CustomerDataDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerDataService {

    private final CustomerDataRepository customerDataRepository;

    public CustomerDataService(CustomerDataRepository customerDataRepository) {
        this.customerDataRepository = customerDataRepository;
    }

    public CustomerData save(CustomerData customerData) {
        return this.customerDataRepository.save(customerData);
    }

    public List<CustomerDataDTO> getAll() {
        return this.customerDataRepository
                .findAll()
                .stream()
                .map(CustomerDataMapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    public CustomerData getById(UUID id) {
        return this.customerDataRepository.findById(id).orElseThrow(() -> new ValidationException(ValidationEnum.CUSTOMER_NOT_FOUND));
    }
}
