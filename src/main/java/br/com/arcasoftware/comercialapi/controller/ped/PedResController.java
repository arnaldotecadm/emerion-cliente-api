package br.com.arcasoftware.comercialapi.controller.ped;

import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.service.ReportService;
import br.com.arcasoftware.comercialapi.application.service.ped.PedResService;
import br.com.arcasoftware.comercialapi.application.service.relatorio.RelatorioService;
import br.com.arcasoftware.comercialapi.controller.relatorio.dto.CurvaABCVendedorDTO;
import br.com.arcasoftware.comercialapi.controller.relatorio.interfaces.ICurvaABCCliente;
import br.com.arcasoftware.comercialapi.model.ReportFull;
import br.com.arcasoftware.comercialapi.model.ReportGenerico;
import br.com.arcasoftwares.model.Pedres;
import br.com.arcasoftwares.model.dto.*;
import br.com.arcasoftwares.model.interfaces.ResumoPorSituacaoInterface;
import br.com.emerion.model.generics.SitResEnum;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pedres")
@CrossOrigin("*")
public class PedResController {

    private final PedResService service;

    @Autowired
    private ReportService reportService;

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    public PedResController(PedResService service) {
        this.service = service;
    }

    @GetMapping(value = {"{numres}"})
    public IPedResInfoDTO getPedInfo(@PathVariable("numres") Integer numres) {
        return service.getPedInfo(numres);
    }

    @GetMapping(value = {"cabecalho-pedido"})
    public List<IPedResDTO> getCabecalhoPedidoList() {
        return service.getCabecalhoPedidoList(99999);
    }

    @GetMapping(value = {"cabecalho-pedido/limit/{recordCount}"})
    public List<IPedResDTO> getCabecalhoPedidoListWithLimit(@PathVariable("recordCount") Integer recordCount) {
        return service.getCabecalhoPedidoList(recordCount);
    }

    @GetMapping(value = {"cabecalho-pedido-cliente/{codcli}"})
    public List<IPedResDTO> getCabecalhoPedidoListByCliente(@PathVariable("codcli") Integer codcli) {
        return service.getCabecalhoPedidoListByCodcli(codcli);
    }

    @GetMapping(value = {"detalhe-pedido/{numres}"})
    public List<IPedRe2DTO> getDetalhesPedido(@PathVariable("numres") String numres) {
        return service.getDetalhesPedido(numres);
    }

    @GetMapping(value = {"cabecalho-pedido/{numres}"})
    public IPedResCab getCabecalhoPedido(@PathVariable("numres") String numres) {
        return service.getCabecalhoPedido(numres);
    }

    @GetMapping(value = {"all", "all/{tipo}"})
    public List<PedResDTO> getAllPedRes(@PathVariable("tipo") Optional<String> tipo) {
        List<Pedres> pedresList = service.getAll();
        List<PedResDTO> pedDTOlist = new ArrayList<>();
        boolean subItems = tipo.isPresent() && tipo.get().equals("full");
        pedresList.forEach(res -> pedDTOlist.add(new PedResDTO(res, subItems)));
        return pedDTOlist;
    }

    @GetMapping(value = "resumo-por-situacao")
    public List<ResumoPorSituacaoInterface> getResumoPorSituacao() {
        return this.service.getResumoPorSituacao();
    }

    @GetMapping(value = {"sitres/{sitres}"})
    public List<IPedResDTO> getBySitResNew(@PathVariable("sitres") SitResEnum sitres) {
        return this.service.getCabecalhoPedidoListBySitres(sitres);
    }

    @GetMapping(value = {"sitres/{sitres}/{tipo}"})
    public List<PedResDTO> getBySitRes(@PathVariable("sitres") String sitres, @PathVariable("tipo") Optional<String> tipo) {
        List<Pedres> pedresList = service.getBySitres(sitres);
        List<PedResDTO> pedDTOlist = new ArrayList<>();
        boolean subItems = tipo.isPresent() && tipo.get().equals("full");
        pedresList.forEach(res -> pedDTOlist.add(new PedResDTO(res, subItems)));
        return pedDTOlist;
    }

    @GetMapping(value = {"numres/{numres}", "numres/{numres}/{tipo}"})
    public PedResDTO getByNumres(@PathVariable("numres") Integer numres, @PathVariable("tipo") Optional<String> tipo) {
        Pedres p = service.getByNumres(numres);
        boolean subItems = tipo.isPresent() && tipo.get().equals("full");
        return new PedResDTO(p, subItems);
    }

    @GetMapping(value = {"codcli/{codcli}", "codcli/{codcli}/{tipo}"})
    public List<PedResDTO> getByCodCli(@PathVariable("codcli") Integer codcli, @PathVariable("tipo") Optional<String> tipo) {
        List<Pedres> pedresList = service.getByCodcli(codcli);
        List<PedResDTO> pedDTOlist = new ArrayList<>();
        boolean subItems = tipo.isPresent() && tipo.get().equals("full");
        pedresList.forEach(res -> pedDTOlist.add(new PedResDTO(res, subItems)));
        return pedDTOlist;
    }

    @GetMapping(value = {"filter/{pedResDTO}", "filter/{pedResDTO}/{tipo}"})
    public List<PedResDTO> getByFilter(@PathVariable String pedResDTO, @PathVariable("tipo") Optional<String> tipo) {
        List<Pedres> pedresList = service.getByFilter(new Gson().fromJson(pedResDTO, PedResDTO.class));
        List<PedResDTO> pedresDTOlist = new ArrayList<>();
        boolean subItems = tipo.isPresent() && tipo.get().equals("full");
        pedresList.forEach(pedres -> pedresDTOlist.add(new PedResDTO(pedres, subItems)));
        return pedresDTOlist;
    }

    @PostMapping(value = "update-status")
    public String updateStatusPedRes(@RequestParam("numres") Integer numres, @RequestParam("sitres") String sitres) {
        this.service.updateStatus(numres, sitres);

        return null;
    }

    @PostMapping(value = "rejeitar-pedido")
    public String rejeitarPedido(@RequestBody UpdateStatusObj updateStatusObj) {
        this.service.rejeitarPedido(updateStatusObj.getNumres(), updateStatusObj.getSitres(), updateStatusObj.getObsres());

        return null;
    }

    @PostMapping(value = "alterar-status-sem-observacao")
    public String alterarStatusPedidoSemObservacao(@RequestBody UpdateStatusObj updateStatusObj) {
        this.service.updateStatusPedidoSemObservacao(updateStatusObj.getNumres(), updateStatusObj.getSitres());

        return null;
    }

    @PostMapping(value = "aprovar-periodo-programacao")
    public String aprovarPeriodoProgramacao(@RequestBody UpdateStatusObj updateStatusObj) {

        this.service.aprovarPeriodoProgramacao(updateStatusObj.getNumres(), updateStatusObj.getSitres(), updateStatusObj.getObsres());

        return null;
    }

    @PostMapping(value = "aprovar-liberacao-dpto-comercial")
    public String aprovarLiberacaoDptoComercial(@RequestBody UpdateStatusObj updateStatusObj) {

        this.service.aprovarLiberacaoDptoComercial(updateStatusObj.getNumres(), updateStatusObj.getSitres(), updateStatusObj.getObsres());

        return null;
    }

    @PostMapping(value = "aprovar-liberacao-dpto-financeiro")
    public ResponseEntity<Boolean> aprovarLiberacaoDptoFinanceiro(@RequestBody UpdateStatusObj params) {

        this.service.aprovarLiberacaoDptoFinanceiro(params.getNumres(), params.getSitres(), params.getObsres());

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping(value = "aprovar-liberacao-consulta-cadastro")
    public ResponseEntity<Boolean> aprovarLiberacaoConsultaCadastro(@RequestBody UpdateStatusObj params) {
        this.service.aprovarLiberacaoConsultaCadastro(params.getNumres(), params.getSitres(), params.getObsres());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping(value = "aprovar-confirmacao-pagamento")
    public ResponseEntity<Boolean> aprovarConfirmacaoPagamento(@RequestBody UpdateStatusObj params) {
        this.service.aprovarConfirmacaoPagamento(params.getNumres(), params.getSitres(), params.getObsres());
        return new ResponseEntity<>(true, HttpStatus.OK);
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

    @GetMapping(path = "curva-abc-clientes")
    public ResponseEntity<InputStreamResource> curvaAbcClientes() throws JRException, IOException {
        List<ICurvaABCCliente> curvaAbcCliente = this.relatorioService.getCurvaAbcCliente();
        ByteArrayOutputStream baos = reportService.impressaoCurvaABCCliente(Collections.singletonList(new ReportGenerico(curvaAbcCliente)));
        return getReport(baos, "curva-abc-de-clientes_");
    }

    @GetMapping(path = "curva-abc-vendedores")
    public ResponseEntity<InputStreamResource> curvaAbcVendedores() throws JRException, IOException {
        List<CurvaABCVendedorDTO> curvaAbcVendedores = this.relatorioService.getCurvaAbcVendedor();
        ByteArrayOutputStream baos = reportService.impressaoCurvaABCVendedor(Collections.singletonList(new ReportGenerico(curvaAbcVendedores)));
        return getReport(baos, "curva-abc-de-vendedores_");
    }


    private ResponseEntity<InputStreamResource> getReport(ByteArrayOutputStream exportReport, String reportName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline; filename=" + reportName + ".pdf");

        byte[] bytes = exportReport.toByteArray();
        InputStream targetStream = new ByteArrayInputStream(bytes);

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(targetStream));
    }

    @GetMapping(value = {"relatorio/cabecalho/{numres}"})
    public IReportPedResHead getReportPedRes(@PathVariable("numres") Integer numres) {
        return service.getReportPedRes(numres);
    }

    @GetMapping(value = {"relatorio/detalhe/{codemp}/{dteres}/{numres}"})
    public ReportFull getReportPedRe2(@PathVariable("codemp") Integer codemp, @PathVariable("dteres") Date dteres, @PathVariable("numres") Integer numres) {
        IReportPedResHead pedInfo = service.getReportPedRes(numres);
        List<IReportPedRe2Detail> reportPedRe2 = service.getReportPedRe2(codemp, dteres, numres);
        return new ReportFull(pedInfo, reportPedRe2);
    }
}

@AllArgsConstructor
@Getter
class UpdateStatusObj {
    Integer numres;
    String sitres;
    String obsres;
}
