package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.CustomerCreditRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerCredit;
import br.com.arcasoftware.comercialapi.mapper.CustomerCreditMapper;
import br.com.arcasoftware.customerapi.model.CustomerCreditDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerCreditService {

    private final CustomerCreditRepository customerCreditRepository;

    public CustomerCreditService(CustomerCreditRepository customerCreditRepository) {
        this.customerCreditRepository = customerCreditRepository;
    }

    public CustomerCredit save(CustomerCredit customer) {
        List<CustomerCreditDTO> customerDataDbList = this.getByCnpjEmpresaAndCodcli(customer.getCnpjEmpresa(), customer.getCodcli());
        if (!customerDataDbList.isEmpty()) {
            Optional<CustomerCreditDTO> creditDTO = customerDataDbList.stream().filter(it -> it.getDtecde().equals(customer.getDtecde()) && it.getSeqcde().equals(customer.getSeqcde())).findFirst();
            creditDTO.ifPresent(data -> customer.setId(creditDTO.get().getId()));
        }
        return this.customerCreditRepository.save(customer);
    }

    public List<CustomerCreditDTO> getAll() {
        return this.customerCreditRepository
                .findAll()
                .stream()
                .map(CustomerCreditMapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    public List<CustomerCreditDTO> getByCnpjEmpresaAndCodcli(String cnpjEmpresa, String codcli) {
        List<CustomerCredit> customerCreditList = this.customerCreditRepository.findByCnpjEmpresaAndCodcli(cnpjEmpresa, codcli);
        return customerCreditList.stream().map(CustomerCreditMapper::toDomainEntity).collect(Collectors.toList());
    }

    public CustomerCredit getById(UUID creditId) {
        return this.customerCreditRepository.findById(creditId).orElseThrow(() -> new ValidationException(ValidationEnum.CREDIT_NOT_FOUND));
    }
}
