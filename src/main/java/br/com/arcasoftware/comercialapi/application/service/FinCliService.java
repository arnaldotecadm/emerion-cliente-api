package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FinCliRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.ClienteDocument;
import br.com.arcasoftware.comercialapi.application.repository.model.Fincde;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.ClienteData;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.DashboardCredito;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.Endereco;
import br.com.arcasoftware.comercialapi.mapper.ClienteDataMapper;
import br.com.arcasoftware.comercialapi.model.dto.IFinCliDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinCliService {

    private FinCliRepository repository;
    private final ClienteDataMapper clienteDataMapper;
    private final FincdeService fincdeService;

    public FinCliService(FinCliRepository repository, ClienteDataMapper clienteDataMapper,
                         FincdeService fincdeService) {
        this.repository = repository;
        this.clienteDataMapper = clienteDataMapper;
        this.fincdeService = fincdeService;
    }

    public List<IFinCliDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }

    public ClienteDocument save(ClienteData clienteData) {
        ClienteDocument document = this.clienteDataMapper.clienteDataToClienteDocument(clienteData);
        return this.repository.save(document);
    }

    public Optional<ClienteDocument> getByCnpjEmpresaAndCodcli(String cnpjEmpresa, long codcli) {
        return this.repository.findByCnpjEmpresaAndCodcli(cnpjEmpresa, codcli);

    }

    public void saveInformacaoTelaInicial(DashboardCredito informacaoTelaInicial) {
        saveFincdeIfApplicable(informacaoTelaInicial);
    }

    public void saveEnderecoCompleto(Endereco end) {

        Optional<ClienteDocument> fincli = this.getByCnpjEmpresaAndCodcli(end.getCnpjEmpresa(), end.getCodcli());
        ClienteDocument cliente;
        if (fincli.isPresent()) {
            cliente = fincli.get();
        } else {
            cliente = new ClienteDocument();
        }

        String tipo = end.getTipo().toUpperCase();

        switch (tipo) {
            case "FATURAMENTO": {
                cliente.setCefcli(end.getCefcli());
                cliente.setTEFCLI(end.getTefcli());
                cliente.setENFCLI(end.getEnfcli());
                cliente.setNRFCLI(end.getNrfcli());
                cliente.setRFFCLI(end.getRffcli());
                cliente.setBAFCLI(end.getBafcli());
                cliente.setCifcli(end.getCifcli());
                cliente.setUffcli(end.getUffcli());
                cliente.setPT1CLI(end.getPt1cli());
                cliente.setFO1CLI(end.getFo1cli());
                cliente.setCOFCLI(end.getCofcli());
                cliente.setPC1CLI(end.getPc1cli());
                cliente.setFC1CLI(end.getFc1cli());
                break;
            }
            case "COBRANCA": {
                cliente.setCeccli(end.getCefcli());
                cliente.setTECCLI(end.getTefcli());
                cliente.setENCCLI(end.getEnfcli());
                cliente.setNRCCLI(end.getNrfcli());
                cliente.setRFCCLI(end.getRffcli());
                cliente.setBACCLI(end.getBafcli());
                cliente.setCICCLI(end.getCifcli());
                cliente.setUFCCLI(end.getUffcli());
                cliente.setPT2CLI(end.getPt1cli());
                cliente.setFO2CLI(end.getFo1cli());
                cliente.setCOCCLI(end.getCofcli());
                cliente.setPC2CLI(end.getPc1cli());
                cliente.setFC2CLI(end.getFc1cli());
                break;
            }
            case "COMPRAS": {
                cliente.setCeacli(end.getCefcli());
                cliente.setTEACLI(end.getTefcli());
                cliente.setENACLI(end.getEnfcli());
                cliente.setNRACLI(end.getNrfcli());
                cliente.setRFACLI(end.getRffcli());
                cliente.setBAACLI(end.getBafcli());
                cliente.setCIACLI(end.getCifcli());
                cliente.setUFACLI(end.getUffcli());
                cliente.setPT3CLI(end.getPt1cli());
                cliente.setFO3CLI(end.getFo1cli());
                cliente.setCOMCLI(end.getCofcli());
                cliente.setPC3CLI(end.getPc1cli());
                cliente.setFC3CLI(end.getFc1cli());
                break;
            }
            case "ENTREGA": {
                cliente.setCeecli(end.getCefcli());
                cliente.setTEECLI(end.getTefcli());
                cliente.setENECLI(end.getEnfcli());
                cliente.setNRECLI(end.getNrfcli());
                cliente.setRFECLI(end.getRffcli());
                cliente.setBAECLI(end.getBafcli());
                cliente.setCIECLI(end.getCifcli());
                cliente.setUFECLI(end.getUffcli());
                cliente.setPT4CLI(end.getPt1cli());
                cliente.setFO4CLI(end.getFo1cli());
                cliente.setCOECLI(end.getCofcli());
                cliente.setPC4CLI(end.getPc1cli());
                cliente.setFC4CLI(end.getFc1cli());
                break;
            }
        }
        Arrays.asList(1,2,3).stream().collect(Collectors.averagingInt(Integer::intValue));

        ClienteData clienteData = this.clienteDataMapper.clienteDocumentToCLienteData(cliente);
        this.save(clienteData);
    }

    private void saveFincdeIfApplicable(DashboardCredito item) {
        Fincde cde = new Fincde();
        cde.setCnpjEmpresa(item.getCnpjEmpresa());
        cde.setCodcli(item.getCodcli());
        cde.setDtecde(item.getDtecde());
        cde.setSeqcde(item.getSeqcde());
        cde.setCodemp(item.getCodemp());
        cde.setUsacde(item.getUsacde());
        cde.setValcde(item.getValcde());
        cde.setSldcde(item.getSldcde());
        try {
            this.fincdeService.save(cde);
        } catch (DataIntegrityViolationException ex) {
            log.error("Um erro ecorreu ao tentar salvar o registro.", ex.getMessage());
        }
    }

}
