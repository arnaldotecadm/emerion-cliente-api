package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.enums.ValidationEnum;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrder;
import br.com.arcasoftware.comercialapi.application.repository.model.CustomerOrderDetail;
import br.com.arcasoftware.comercialapi.application.service.CustomerOrderDetailService;
import br.com.arcasoftware.comercialapi.application.service.CustomerOrderService;
import br.com.arcasoftware.comercialapi.application.service.ReportService;
import br.com.arcasoftware.comercialapi.model.ReportFull;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/pedres")
@CrossOrigin("*")
@Slf4j
public class PedResController {

    private final ReportService reportService;
    private final CustomerOrderService customerOrderService;
    private final CustomerOrderDetailService customerOrderDetailService;

    @Autowired
    public PedResController(ReportService reportService, CustomerOrderService customerOrderService,
                            CustomerOrderDetailService customerOrderDetailService) {
        this.reportService = reportService;
        this.customerOrderService = customerOrderService;
        this.customerOrderDetailService = customerOrderDetailService;
    }

    @GetMapping(path = "impressao_pedido/{codemp}/{dteres}/{numres}")
    public ResponseEntity<InputStreamResource> impressaoPedido(@PathVariable("codemp") Integer codemp, @PathVariable("dteres") Date dteres, @PathVariable("numres") Integer numres) {
        try {
            /***
             * TODO CNPJEMPRESA is HARD-CODED here
             */
            CustomerOrder customerOrder = this.customerOrderService.getAllByCnpjEmpresaAndNumres("03089573000121", numres.toString())
                    .orElseThrow(() -> new ValidationException(ValidationEnum.ORDER_NOT_FOUND));
            List<CustomerOrderDetail> orderDetailList = this.customerOrderDetailService.getByCustomerOrder(customerOrder.getId());

            ByteArrayOutputStream exportReport = reportService.impressaoPedido(Collections.singletonList(new ReportFull(customerOrder, orderDetailList)));
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "inline; filename=impressaoPedido_" + numres + ".pdf");

            byte[] bytes = exportReport.toByteArray();
            InputStream targetStream = new ByteArrayInputStream(bytes);

            return ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(targetStream));
        } catch (JRException | IOException e) {
            log.error(e.getMessage());
            throw new ValidationException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
