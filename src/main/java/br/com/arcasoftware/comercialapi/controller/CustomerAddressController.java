package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerAddress;
import br.com.arcasoftware.comercialapi.application.service.CustomerAddressService;
import br.com.arcasoftware.comercialapi.mapper.CustomerAddressMapper;
import br.com.arcasoftware.customerapi.controller.CustomerAddressApi;
import br.com.arcasoftware.customerapi.model.CustomerAddressDTO;
import br.com.arcasoftware.customerapi.model.CustomerAddressResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class CustomerAddressController implements CustomerAddressApi {

    private final CustomerAddressService customerAddressService;

    @Autowired
    public CustomerAddressController(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }

    @Override
    public ResponseEntity<List<CustomerAddressResponseDTO>> getAllCustomerAddress() {
        List<CustomerAddressResponseDTO> customerAddressResponseDTOList = this.customerAddressService.getAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(customerAddressResponseDTOList);
    }

    @Override
    public ResponseEntity<CustomerAddressResponseDTO> getCustomerAddressById(UUID customerAddressId) {
        CustomerAddress customerAddress = this.customerAddressService.getById(customerAddressId);
        return ResponseEntity.ok(CustomerAddressMapper.toResponseDto(customerAddress));
    }

    @Override
    public ResponseEntity<List<CustomerAddressResponseDTO>> saveCustomerAddress(CustomerAddressDTO customerAddressDTO) {
        List<CustomerAddress> customerAddressList = this.customerAddressService.saveAll(CustomerAddressMapper.toDatabaseEntity(customerAddressDTO));
        List<CustomerAddressResponseDTO> responseDTOList = customerAddressList.stream().map(CustomerAddressMapper::toResponseDto).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOList);
    }
}
