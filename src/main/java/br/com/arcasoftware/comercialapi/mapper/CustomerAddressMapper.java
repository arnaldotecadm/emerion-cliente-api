package br.com.arcasoftware.comercialapi.mapper;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerAddress;
import br.com.arcasoftware.customerapi.model.CustomerAddressDTO;
import br.com.arcasoftware.customerapi.model.CustomerAddressDetailDTO;
import br.com.arcasoftware.customerapi.model.CustomerAddressResponseDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerAddressMapper {

    private CustomerAddressMapper(){
        // NO_OP
    }
    public static List<CustomerAddress> toDatabaseEntity(CustomerAddressDTO customerAddressDTO) {
        List<CustomerAddress> customerAddressList = new ArrayList<>();
        for (CustomerAddressDetailDTO detailDTO : customerAddressDTO.getAddressList()) {
            CustomerAddress customerAddress = new CustomerAddress();
            BeanUtils.copyProperties(detailDTO, customerAddress);
            customerAddress.setCnpjEmpresa(customerAddressDTO.getCnpjEmpresa());
            customerAddress.setCodcli(customerAddressDTO.getCodcli());
            customerAddressList.add(customerAddress);
        }
        return customerAddressList;
    }

    public static CustomerAddressDTO toDomainEntity(List<CustomerAddress> customerAddressList) {
        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
        customerAddressDTO.setAddressList(new ArrayList<>());
        if (null != customerAddressList && !customerAddressList.isEmpty()) {
            for (CustomerAddress customerAddress : customerAddressList) {
                customerAddressDTO.setCodcli(customerAddress.getCodcli());
                customerAddressDTO.setCnpjEmpresa(customerAddress.getCnpjEmpresa());
                CustomerAddressDetailDTO detailDTO = new CustomerAddressDetailDTO();
                BeanUtils.copyProperties(customerAddress, detailDTO);
                customerAddressDTO.getAddressList().add(detailDTO);
            }
        }
        return customerAddressDTO;
    }

    public static CustomerAddressResponseDTO toResponseDto(CustomerAddress customerAddress) {
        CustomerAddressResponseDTO customerAddressResponseDTO = new CustomerAddressResponseDTO();
        BeanUtils.copyProperties(customerAddress, customerAddressResponseDTO);
        return customerAddressResponseDTO;
    }
}
