package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.service.CustomerAddressService;
import br.com.arcasoftware.comercialapi.application.service.CustomerCreditService;
import br.com.arcasoftware.comercialapi.application.service.CustomerDataService;
import br.com.arcasoftware.comercialapi.mapper.CustomerDataMapper;
import br.com.arcasoftware.customerapi.controller.CustomerDashboardApi;
import br.com.arcasoftware.customerapi.model.CustomerAddressResponseDTO;
import br.com.arcasoftware.customerapi.model.CustomerCreditDTO;
import br.com.arcasoftware.customerapi.model.CustomerDashboardInfoDTO;
import br.com.arcasoftware.customerapi.model.CustomerDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class CustomerDashboardController implements CustomerDashboardApi {

    private final CustomerAddressService customerAddressService;
    private final CustomerCreditService customerCreditService;
    private final CustomerDataService customerDataService;

    @Autowired
    public CustomerDashboardController(CustomerAddressService customerAddressService, CustomerCreditService customerCreditService,
                                       CustomerDataService customerDataService) {
        this.customerAddressService = customerAddressService;
        this.customerCreditService = customerCreditService;
        this.customerDataService = customerDataService;
    }

    @Override
    public ResponseEntity<CustomerDashboardInfoDTO> getCustomerDashboard(UUID customerId) {
        CustomerDataDTO customerDataDTO = CustomerDataMapper.toDomainEntity(this.customerDataService.getById(customerId));
        List<CustomerAddressResponseDTO> customerAddressResponseDTO = this.customerAddressService.getByCnpjEmpresaAndCodcli(customerDataDTO.getCnpjEmpresa(), customerDataDTO.getCodcli());
        List<CustomerCreditDTO> customerCreditDTOList = this.customerCreditService.getByCnpjEmpresaAndCodcli(customerDataDTO.getCnpjEmpresa(), customerDataDTO.getCodcli());
        CustomerDashboardInfoDTO customerDashboardInfoDTO = new CustomerDashboardInfoDTO();
        customerDashboardInfoDTO.setCustomerData(customerDataDTO);
        customerDashboardInfoDTO.setCustomerAddress(customerAddressResponseDTO);
        customerDashboardInfoDTO.setCustomerCreditList(customerCreditDTOList);
        return ResponseEntity.ok(customerDashboardInfoDTO);
    }
}
