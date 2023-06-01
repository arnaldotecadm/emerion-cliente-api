package br.com.arcasoftware.comercialapi.model;

import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrder;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReportFull {
    CustomerOrder pedres;
    List<CustomerOrderDetail> pedre2;
}
