package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FinCliRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.ClienteDocument;
import br.com.arcasoftware.comercialapi.application.repository.model.Fincde;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.ClienteData;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.DashboardEndereco;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.Endereco;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.EnderecoCompleto;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.InformacaoTelaInicial;
import br.com.arcasoftware.comercialapi.mapper.ClienteDataMapper;
import br.com.arcasoftware.comercialapi.model.dto.IFinCliDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void saveInformacaoTelaInicial(InformacaoTelaInicial informacaoTelaInicial) {
        saveFincdeIfApplicable(informacaoTelaInicial);

        saveClienteEnderecoIfApplicable(informacaoTelaInicial);
    }

    private void saveClienteEnderecoIfApplicable(InformacaoTelaInicial informacaoTelaInicial) {
        String cefcli = "";
        String ceccli = "";
        String ceacli = "";
        String ceecli = "";
        for (DashboardEndereco end : informacaoTelaInicial.getDashboardEndereco()) {
            String tipo = end.getTipo().toUpperCase();

            switch (tipo) {
                case "FATURAMENTO": {
                    cefcli = end.getCep();
                    break;
                }
                case "COBRANCA": {
                    ceccli = end.getCep();
                    break;
                }
                case "COMPRAS": {
                    ceacli = end.getCep();
                    break;
                }
                case "ENTREGA": {
                    ceecli = end.getCep();
                    break;
                }
            }
        }

        Optional<ClienteDocument> fincli = this.getByCnpjEmpresaAndCodcli(informacaoTelaInicial.getCnpjEmpresa(), informacaoTelaInicial.getCodcli());
        ClienteDocument cliente;
        if (fincli.isPresent()) {
            cliente = fincli.get();
        } else {
            cliente = new ClienteDocument();
            cliente.setCnpjEmpresa(informacaoTelaInicial.getCnpjEmpresa());
            cliente.setCodcli(informacaoTelaInicial.getCodcli());
        }

        cliente.setCefcli(cefcli);
        cliente.setCeccli(ceccli);
        cliente.setCeacli(ceacli);
        cliente.setCeecli(ceecli);

        ClienteData clienteData = this.clienteDataMapper.clienteDocumentToCLienteData(cliente);
        this.save(clienteData);
    }

    public void saveEnderecoCompleto(EnderecoCompleto enderecoCompleto) {

        Optional<ClienteDocument> fincli = this.getByCnpjEmpresaAndCodcli(enderecoCompleto.getCnpjEmpresa(), enderecoCompleto.getCodcli());
        ClienteDocument cliente;
        if (fincli.isPresent()) {
            cliente = fincli.get();
        } else {
            cliente = new ClienteDocument();
        }

        for (Endereco end : enderecoCompleto.getEndereco()) {
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
        }

        ClienteData clienteData = this.clienteDataMapper.clienteDocumentToCLienteData(cliente);
        this.save(clienteData);
    }

    private void saveFincdeIfApplicable(InformacaoTelaInicial informacaoTelaInicial) {
        informacaoTelaInicial.getDashboardCredito().forEach(item -> {
            Fincde cde = new Fincde();
            cde.setCnpjEmpresa(informacaoTelaInicial.getCnpjEmpresa());
            cde.setCodcli(informacaoTelaInicial.getCodcli());
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
        });
    }

}
