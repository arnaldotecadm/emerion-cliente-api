package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerData;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrder;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrderDetail;
import br.com.arcasoftware.comercialapi.application.service.CustomerDataService;
import br.com.arcasoftware.comercialapi.application.service.CustomerOrderDetailService;
import br.com.arcasoftware.comercialapi.application.service.CustomerOrderService;
import br.com.arcasoftware.comercialapi.mapper.CustomerOrderDetailMapper;
import br.com.arcasoftware.comercialapi.mapper.CustomerOrderMapper;
import br.com.arcasoftware.comercialapi.mapper.CustomerOrderReportMapper;
import br.com.arcasoftware.customerapi.controller.CustomerOrderApi;
import br.com.arcasoftware.customerapi.model.CustomerOrderDetailSummaryDTO;
import br.com.arcasoftware.customerapi.model.CustomerOrderHeaderDTO;
import br.com.arcasoftware.customerapi.model.CustomerOrderReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class CustomerOrderController implements CustomerOrderApi {

    private final CustomerOrderService customerOrderService;
    private final CustomerDataService customerDataService;
    private final CustomerOrderDetailService customerOrderDetailService;

    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService,
                                   CustomerDataService customerDataService,
                                   CustomerOrderDetailService customerOrderDetailService) {
        this.customerOrderService = customerOrderService;
        this.customerDataService = customerDataService;
        this.customerOrderDetailService = customerOrderDetailService;
    }

    @Override
    public ResponseEntity<List<CustomerOrderHeaderDTO>> getAllCustomerOrderHeader() {
        List<CustomerOrder> customerOrderList = this.customerOrderService.getAll();
        List<CustomerOrderHeaderDTO> headerDTOList = customerOrderList
                .stream()
                .map(CustomerOrderMapper::toDomainEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(headerDTOList);
    }

    @Override
    public ResponseEntity<List<CustomerOrderHeaderDTO>> getCustomerOrderHeaderByCustomer(UUID customerId) {
        CustomerData customerData = this.customerDataService.getById(customerId);
        List<CustomerOrder> customerOrderList = this.customerOrderService.getAllByCnpjEmpresaAndCodcli(customerData.getCnpjEmpresa(), customerData.getCodcli());
        List<CustomerOrderHeaderDTO> headerDTOList = customerOrderList
                .stream()
                .map(CustomerOrderMapper::toDomainEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(headerDTOList);
    }

    @Override
    public ResponseEntity<CustomerOrderHeaderDTO> getCustomerOrderHeaderByOrderId(UUID orderId) {
        CustomerOrder customerOrder = this.customerOrderService.getById(orderId);
        CustomerOrderHeaderDTO orderHeaderDTO = CustomerOrderMapper.toDomainEntity(customerOrder);
        return ResponseEntity.ok(orderHeaderDTO);
    }

    @Override
    public ResponseEntity<CustomerOrderHeaderDTO> saveCustomerOrderHeader(CustomerOrderHeaderDTO customerOrderHeaderDTO) {
        CustomerOrder customerOrder = CustomerOrderMapper.toDatabaseEntity(customerOrderHeaderDTO);
        CustomerOrder order = this.customerOrderService.save(customerOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerOrderMapper.toDomainEntity(order));
    }

    @Override
    public ResponseEntity<CustomerOrderReportDTO> saveCustomerOrderReportInfo(CustomerOrderReportDTO customerOrderReportDTO) {
        CustomerOrder customerOrder = CustomerOrderReportMapper.toDatabaseCustomerOrderEntity(customerOrderReportDTO);
        List<CustomerOrderDetail> customerOrderDetailList = CustomerOrderReportMapper.toDatabaseCustomerOrderDetailEntity(customerOrderReportDTO);
        CustomerOrder order = this.customerOrderService.save(customerOrder);
        List<CustomerOrderDetail> orderDetailList = new ArrayList<>();
        for (CustomerOrderDetail cod : customerOrderDetailList) {
            cod.setCustomerOrder(order.getId());
            cod.setCnpjEmpresa(order.getCnpjEmpresa());
            cod.setCodcli(order.getCodcli());
            CustomerOrderDetail orderDetail = this.customerOrderDetailService.save(cod);
            orderDetailList.add(orderDetail);
        }
        CustomerOrderReportDTO orderReportDTO = CustomerOrderReportMapper.toDomainEntity(order, orderDetailList);
        return ResponseEntity.ok(orderReportDTO);
    }

    @Override
    public ResponseEntity<List<CustomerOrderDetailSummaryDTO>> getAllCustomerOrderDetail() {
        List<CustomerOrderDetail> orderDetailList = this.customerOrderDetailService.getAll();
        List<CustomerOrderDetailSummaryDTO> summaryDTOList = orderDetailList
                .stream()
                .map(CustomerOrderDetailMapper::toDomainEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(summaryDTOList);
    }

    @Override
    public ResponseEntity<List<CustomerOrderDetailSummaryDTO>> getCustomerOrderDetailByCustomer(UUID customerId) {
        CustomerData customerData = this.customerDataService.getById(customerId);
        List<CustomerOrderDetail> customerOrderDetailList = this.customerOrderDetailService.getAllByCnpjEmpresaAndCodcli(customerData.getCnpjEmpresa(), customerData.getCodcli());
        List<CustomerOrderDetailSummaryDTO> summaryDTOList = customerOrderDetailList
                .stream()
                .map(CustomerOrderDetailMapper::toDomainEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(summaryDTOList);
    }

    @Override
    public ResponseEntity<List<CustomerOrderDetailSummaryDTO>> getCustomerOrderDetailByOrderId(UUID orderId) {
        List<CustomerOrderDetail> customerOrderDetailList = this.customerOrderDetailService.getByCustomerOrder(orderId);
        List<CustomerOrderDetailSummaryDTO> summaryDTOList = customerOrderDetailList
                .stream()
                .map(CustomerOrderDetailMapper::toDomainEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(summaryDTOList);
    }

    @Override
    public ResponseEntity<CustomerOrderDetailSummaryDTO> saveCustomerOrderDetail(CustomerOrderDetailSummaryDTO customerOrderDetailSummaryDTO) {
        CustomerOrder customerOrder = customerOrderService.getAllByCnpjEmpresaAndNumres(
                        customerOrderDetailSummaryDTO.getCnpjEmpresa(),
                        customerOrderDetailSummaryDTO.getNumres())
                .orElseThrow(() -> new ValidationException(ValidationEnum.ORDER_NOT_FOUND));
        CustomerOrderDetail customerOrderDetail = CustomerOrderDetailMapper.toDatabaseEntity(customerOrderDetailSummaryDTO);
        customerOrderDetail.setCustomerOrder(customerOrder.getId());
        CustomerOrderDetail orderDetail = this.customerOrderDetailService.save(customerOrderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerOrderDetailMapper.toDomainEntity(orderDetail));
    }
}
