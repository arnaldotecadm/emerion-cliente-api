package br.com.arcasoftware.comercialapi.controller.areacliente.controller;

import br.com.arcasoftware.comercialapi.application.service.ped.PedResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("download")
@CrossOrigin("*")
public class DownloadController {

    private final PedResService service;

    @Autowired
    public DownloadController(PedResService service) {
        this.service = service;
    }


    @GetMapping(value = {"xml-pedido/{numres}"})
    public HttpEntity<Object> downloadFileXML(@PathVariable("numres") Integer numres) {
        byte[] bytes = this.service.getNfePedido(numres);

        if(null == bytes){
            return new ResponseEntity<>("Recuso solicitado não encontrado", HttpStatus.OK);
        }

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", String.format("attachment; filename=%s.xml", numres));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(bytes.length)
                .contentType(MediaType.APPLICATION_XML)
                .body(resource);
    }
}
