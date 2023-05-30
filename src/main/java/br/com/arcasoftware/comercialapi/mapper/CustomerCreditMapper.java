package br.com.arcasoftware.comercialapi.mapper;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerCredit;
import br.com.arcasoftware.customerapi.model.CustomerCreditDTO;
import org.springframework.beans.BeanUtils;

public class CustomerCreditMapper {

    private CustomerCreditMapper(){
        // NO_OP
    }

    public static CustomerCredit toDatabaseEntity(CustomerCreditDTO customerCreditDTO) {
        CustomerCredit customerCredit = new CustomerCredit();
        BeanUtils.copyProperties(customerCreditDTO, customerCredit);
        return customerCredit;
    }

    public static CustomerCreditDTO toDomainEntity(CustomerCredit customerCredit) {
        CustomerCreditDTO customerCreditDTO = new CustomerCreditDTO();
        BeanUtils.copyProperties(customerCredit, customerCreditDTO);
        return customerCreditDTO;
    }
}
