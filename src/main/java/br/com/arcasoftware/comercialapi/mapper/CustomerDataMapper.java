package br.com.arcasoftware.comercialapi.mapper;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerData;
import br.com.arcasoftware.customerapi.model.CustomerDataDTO;
import org.springframework.beans.BeanUtils;

public class CustomerDataMapper {

    private CustomerDataMapper(){
        // NO_OP
    }
    public static CustomerData toDatabaseEntity(CustomerDataDTO customerDataDTO) {
        CustomerData customerData = new CustomerData();
        BeanUtils.copyProperties(customerDataDTO, customerData);
        return customerData;
    }

    public static CustomerDataDTO toDomainEntity(CustomerData customerData) {
        CustomerDataDTO customerDataDTO = new CustomerDataDTO();
        BeanUtils.copyProperties(customerData, customerDataDTO);
        return customerDataDTO;
    }
}
