package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.CustomerAddressRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerAddress;
import br.com.arcasoftware.comercialapi.mapper.CustomerAddressMapper;
import br.com.arcasoftware.customerapi.model.CustomerAddressResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }

    public List<CustomerAddress> saveAll(List<CustomerAddress> customerAddressList) {
        List<CustomerAddress> customerAddressListDb = new ArrayList<>();
        for (CustomerAddress customerAddress : customerAddressList) {
            CustomerAddress address = this.save(customerAddress);
            customerAddressListDb.add(address);
        }
        return customerAddressListDb;
    }

    public CustomerAddress save(CustomerAddress customer) {
        List<CustomerAddressResponseDTO> customerDataDbList = this.getByCnpjEmpresaAndCodcli(customer.getCnpjEmpresa(), customer.getCodcli());
        if (!customerDataDbList.isEmpty()) {
            Optional<CustomerAddressResponseDTO> responseDTO = customerDataDbList.stream().filter(i -> i.getTipo().equals(customer.getTipo())).findFirst();
            responseDTO.ifPresent(customerAddressResponseDTO -> customer.setId(customerAddressResponseDTO.getId()));
        }
        return this.customerAddressRepository.save(customer);
    }

    public List<CustomerAddressResponseDTO> getAll() {
        List<CustomerAddress> customerAddressList = this.customerAddressRepository.findAll();
        return customerAddressList.stream().map(CustomerAddressMapper::toResponseDto).collect(Collectors.toList());
    }

    public List<CustomerAddressResponseDTO> getByCnpjEmpresaAndCodcli(String cnpjEmpresa, String codcli) {
        List<CustomerAddress> customerAddressList = this.customerAddressRepository.findByCnpjEmpresaAndCodcli(cnpjEmpresa, codcli);
        return customerAddressList.stream().map(CustomerAddressMapper::toResponseDto).collect(Collectors.toList());
    }

    public CustomerAddress getById(UUID id) {
        return this.customerAddressRepository.findById(id).orElseThrow(() -> new ValidationException(ValidationEnum.ADDRESS_NOT_FOUND));
    }
}
