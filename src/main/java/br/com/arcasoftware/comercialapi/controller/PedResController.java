package br.com.arcasoftware.comercialapi.controller;

import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.service.ReportService;
import br.com.arcasoftware.comercialapi.application.service.PedResService;
import br.com.arcasoftware.comercialapi.model.ReportFull;
import br.com.arcasoftwares.model.dto.*;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class PedResController {

    private final PedResService service;

    @Autowired
    private ReportService reportService;

    @Autowired
    public PedResController(PedResService service) {
        this.service = service;
    }

    @GetMapping(value = {"cabecalho-pedido"})
    public List<IPedResDTO> getCabecalhoPedidoList() {
        return service.getCabecalhoPedidoList(99999);
    }

    @GetMapping(value = {"cabecalho-pedido-cliente/{codcli}"})
    public List<IPedResDTO> getCabecalhoPedidoListByCliente(@PathVariable("codcli") Integer codcli) {
        return service.getCabecalhoPedidoListByCodcli(codcli);
    }

    @GetMapping(value = {"cabecalho-pedido/{numres}"})
    public IPedResCab getCabecalhoPedido(@PathVariable("numres") int numres) {
        return service.getCabecalhoPedido(numres);
    }

    @GetMapping(value = {"detalhe-pedido/{numres}"})
    public List<IPedRe2DTO> getDetalhesPedido(@PathVariable("numres") int numres) {
        return service.getDetalhesPedido(numres);
    }

    @GetMapping(path = "impressao_pedido/{codemp}/{dteres}/{numres}")
    public ResponseEntity<InputStreamResource> impressaoPedido(@PathVariable("codemp") Integer codemp, @PathVariable("dteres") Date dteres, @PathVariable("numres") Integer numres) {
        try {
            IReportPedResHead pedInfo = service.getReportPedRes(numres);
            List<IReportPedRe2Detail> reportPedRe2 = service.getReportPedRe2(codemp, dteres, numres);

            ByteArrayOutputStream exportReport = reportService.impressaoPedido(Collections.singletonList(new ReportFull(pedInfo, reportPedRe2)));
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
            throw new ValidationException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
