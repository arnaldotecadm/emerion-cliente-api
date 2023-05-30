package br.com.arcasoftware.comercialapi.mapper;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrder;
import br.com.arcasoftware.customerapi.model.CustomerOrderHeaderDTO;
import org.springframework.beans.BeanUtils;

public class CustomerOrderMapper {

    private CustomerOrderMapper(){
        // NO_OP
    }
    public static CustomerOrder toDatabaseEntity(CustomerOrderHeaderDTO customerOrderHeaderDTO) {
        CustomerOrder customerorder = new CustomerOrder();
        BeanUtils.copyProperties(customerOrderHeaderDTO, customerorder);
        return customerorder;
    }

    public static CustomerOrderHeaderDTO toDomainEntity(CustomerOrder customerOrder) {
        CustomerOrderHeaderDTO customerOrderHeaderDTO = new CustomerOrderHeaderDTO();
        BeanUtils.copyProperties(customerOrder, customerOrderHeaderDTO);
        return customerOrderHeaderDTO;
    }
}
