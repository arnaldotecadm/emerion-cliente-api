package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerData;
import br.com.arcasoftware.comercialapi.application.service.CustomerDataService;
import br.com.arcasoftware.comercialapi.mapper.CustomerDataMapper;
import br.com.arcasoftware.customerapi.controller.CustomerDataApi;
import br.com.arcasoftware.customerapi.model.CustomerDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class CustomerDataController implements CustomerDataApi {

    private final CustomerDataService customerDataService;

    @Autowired
    public CustomerDataController(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    @Override
    public ResponseEntity<CustomerDataDTO> saveCustomerData(@RequestBody CustomerDataDTO customerDataRequest) {
        CustomerData customerData = this.customerDataService.save(CustomerDataMapper.toDatabaseEntity(customerDataRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerDataMapper.toDomainEntity(customerData));
    }

    @Override
    public ResponseEntity<List<CustomerDataDTO>> getAllCustomerData() {
        List<CustomerDataDTO> dataDTOList = this.customerDataService.getAll();
        return ResponseEntity.ok(dataDTOList);
    }

    @Override
    public ResponseEntity<CustomerDataDTO> getCustomerData(UUID customerId) {
        CustomerData customerData = this.customerDataService.getById(customerId);
        return ResponseEntity.ok(CustomerDataMapper.toDomainEntity(customerData));
    }
}
