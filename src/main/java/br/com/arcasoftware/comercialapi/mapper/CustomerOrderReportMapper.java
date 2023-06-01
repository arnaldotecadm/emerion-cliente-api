package br.com.arcasoftware.comercialapi.mapper;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrder;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrderDetail;
import br.com.arcasoftware.customerapi.model.CustomerOrderReportDTO;
import br.com.arcasoftware.customerapi.model.CustomerOrderReportItemDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderReportMapper {

    private CustomerOrderReportMapper() {
        // NO_OP
    }

    public static CustomerOrder toDatabaseCustomerOrderEntity(CustomerOrderReportDTO customerOrderReportDTO) {
        CustomerOrder customerorder = new CustomerOrder();
        BeanUtils.copyProperties(customerOrderReportDTO, customerorder);
        return customerorder;
    }

    public static List<CustomerOrderDetail> toDatabaseCustomerOrderDetailEntity(CustomerOrderReportDTO customerOrderReportDTO) {
        List<CustomerOrderDetail> customerOrderDetailList = new ArrayList<>();
        for (CustomerOrderReportItemDTO dto : customerOrderReportDTO.getItems()) {
            CustomerOrderDetail customerOrderDetail = new CustomerOrderDetail();
            BeanUtils.copyProperties(dto, customerOrderDetail);
            customerOrderDetailList.add(customerOrderDetail);
        }
        return customerOrderDetailList;
    }

    public static CustomerOrderReportDTO toDomainEntity(CustomerOrder customerOrder, List<CustomerOrderDetail> customerOrderDetailList){
        CustomerOrderReportDTO customerOrderReportDTO = new CustomerOrderReportDTO();
        BeanUtils.copyProperties(customerOrder, customerOrderReportDTO);
        List<CustomerOrderReportItemDTO> customerOrderReportItemDTOList = new ArrayList<>();
        for(CustomerOrderDetail customerOrderDetail : customerOrderDetailList){
            CustomerOrderReportItemDTO customerOrderReportItemDTO = new CustomerOrderReportItemDTO();
            BeanUtils.copyProperties(customerOrderDetail, customerOrderReportItemDTO);
            customerOrderReportItemDTOList.add(customerOrderReportItemDTO);
        }
        customerOrderReportDTO.setItems(customerOrderReportItemDTOList);

        return customerOrderReportDTO;
    }

}
