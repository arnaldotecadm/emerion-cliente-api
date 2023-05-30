package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerCredit;
import br.com.arcasoftware.comercialapi.application.service.CustomerCreditService;
import br.com.arcasoftware.comercialapi.mapper.CustomerCreditMapper;
import br.com.arcasoftware.customerapi.controller.CustomerCreditApi;
import br.com.arcasoftware.customerapi.model.CustomerCreditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class CustomerCreditController implements CustomerCreditApi {

    private final CustomerCreditService customerCreditService;

    @Autowired
    public CustomerCreditController(CustomerCreditService customerCreditService) {
        this.customerCreditService = customerCreditService;
    }

    @Override
    public ResponseEntity<List<CustomerCreditDTO>> getAllCustomerCredit() {
        List<CustomerCreditDTO> creditDTOList = this.customerCreditService.getAll();
        return ResponseEntity.ok(creditDTOList);
    }

    @Override
    public ResponseEntity<CustomerCreditDTO> getCustomerCreditById(UUID creditId) {
        CustomerCredit customerCredit = this.customerCreditService.getById(creditId);
        return ResponseEntity.ok(CustomerCreditMapper.toDomainEntity(customerCredit));
    }

    @Override
    public ResponseEntity<CustomerCreditDTO> saveCustomerCredit(CustomerCreditDTO customerCreditDTO) {
        CustomerCredit customerCredit = this.customerCreditService.save(CustomerCreditMapper.toDatabaseEntity(customerCreditDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerCreditMapper.toDomainEntity(customerCredit));
    }
}
