package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.CustomerOrderDetailRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerOrderDetailService {

    private final CustomerOrderDetailRepository customerOrderDetailRepository;

    public CustomerOrderDetailService(CustomerOrderDetailRepository customerOrderDetailRepository) {
        this.customerOrderDetailRepository = customerOrderDetailRepository;
    }

    public CustomerOrderDetail save(CustomerOrderDetail customerOrderDetail) {
        List<CustomerOrderDetail> customerOrderDetailList = this.getByCnpjEmpresaAndNumres(customerOrderDetail.getCnpjEmpresa(), customerOrderDetail.getNumres());
        if (!customerOrderDetailList.isEmpty()) {
            Optional<CustomerOrderDetail> orderDetail = customerOrderDetailList.stream().filter(it ->
                    it.getProduto().equalsIgnoreCase(customerOrderDetail.getProduto()) &&
                            it.getNumres().equals(customerOrderDetail.getNumres())
            ).findFirst();
            orderDetail.ifPresent(data -> customerOrderDetail.setId(orderDetail.get().getId()));
        }
        return this.customerOrderDetailRepository.save(customerOrderDetail);
    }

    public List<CustomerOrderDetail> getAll() {
        return this.customerOrderDetailRepository.findAll();
    }

    public List<CustomerOrderDetail> getAllByCnpjEmpresaAndCodcli(String cnpjEmprsa, String codcli) {
        return this.customerOrderDetailRepository.findByCnpjEmpresaAndCodcli(cnpjEmprsa, codcli);
    }

    public List<CustomerOrderDetail> getByCnpjEmpresaAndNumres(String cnpjEmprsa, String numres) {
        return this.customerOrderDetailRepository.findByCnpjEmpresaAndNumres(cnpjEmprsa, numres);
    }

    public List<CustomerOrderDetail> getByCustomerOrder(UUID customerOrder) {
        return this.customerOrderDetailRepository.findByCustomerOrder(customerOrder);
    }
}
