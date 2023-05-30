package br.com.arcasoftware.comercialapi.mapper;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrderDetail;
import br.com.arcasoftware.customerapi.model.CustomerOrderDetailSummaryDTO;
import org.springframework.beans.BeanUtils;

public class CustomerOrderDetailMapper {

    private CustomerOrderDetailMapper(){
        // NO_OP
    }
    public static CustomerOrderDetail toDatabaseEntity(CustomerOrderDetailSummaryDTO customerOrderDetailSummaryDTO) {
        CustomerOrderDetail customerOrderDetail = new CustomerOrderDetail();
        BeanUtils.copyProperties(customerOrderDetailSummaryDTO, customerOrderDetail);
        return customerOrderDetail;
    }

    public static CustomerOrderDetailSummaryDTO toDomainEntity(CustomerOrderDetail customerOrderDetail) {
        CustomerOrderDetailSummaryDTO customerOrderDetailSummaryDTO = new CustomerOrderDetailSummaryDTO();
        BeanUtils.copyProperties(customerOrderDetail, customerOrderDetailSummaryDTO);
        return customerOrderDetailSummaryDTO;
    }
}
