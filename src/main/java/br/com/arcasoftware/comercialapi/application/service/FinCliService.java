package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FinCliRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.ClienteDocument;
import br.com.arcasoftware.comercialapi.application.repository.model.Fincde;
import br.com.arcasoftware.comercialapi.application.repository.model.Finregtrib;
import br.com.arcasoftware.comercialapi.application.repository.model.TipIndicIe;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.ClienteData;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.DashboardEndereco;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.Endereco;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.EnderecoCompleto;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.InformacaoTelaInicial;
import br.com.arcasoftware.comercialapi.mapper.ClienteDataMapper;
import br.com.arcasoftware.comercialapi.model.dto.IFinCliDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinCliService {

    private FinCliRepository repository;
    private final ClienteDataMapper clienteDataMapper;
    private final FinregtribService finregtribService;
    private final FincdeService fincdeService;
    private final TipIndicIeService tipIndicIeService;

    public FinCliService(FinCliRepository repository, ClienteDataMapper clienteDataMapper, FinregtribService finregtribService,
                         FincdeService fincdeService, TipIndicIeService tipIndicIeService) {
        this.repository = repository;
        this.clienteDataMapper = clienteDataMapper;
        this.finregtribService = finregtribService;
        this.fincdeService = fincdeService;
        this.tipIndicIeService = tipIndicIeService;
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
        saveFinregTribIfApplicable(informacaoTelaInicial);

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
        String cefcli = "";
        String ceccli = "";
        String ceacli = "";
        String ceecli = "";

        for (Endereco end : enderecoCompleto.getEndereco()) {
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

    private void saveFincdeIfApplicable(InformacaoTelaInicial informacaoTelaInicial) {
        informacaoTelaInicial.getDashboardCredito().forEach(item -> {
            Optional<Finregtrib> fincde = this.fincdeService.getByCnpjEmpresaAndCodempAndDtecdeAndSeqcde(informacaoTelaInicial.getCnpjEmpresa(), item.getCodemp(), item.getDtecde(), item.getSeqcde());
            if (!fincde.isPresent()) {
                Fincde cde = new Fincde();
                cde.setCnpjEmpresa(informacaoTelaInicial.getCnpjEmpresa());
                cde.setCodcli(informacaoTelaInicial.getCodcli());
                cde.setDtecde(item.getDtecde());
                cde.setSeqcde(item.getSeqcde());
                cde.setCodemp(item.getCodemp());
                cde.setUsacde(item.getUsacde());
                cde.setValcde(item.getValcde());

                this.fincdeService.save(cde);
            }
        });
    }

    private void saveIndicIeIfApplicable(InformacaoTelaInicial informacaoTelaInicial) {
        Optional<TipIndicIe> tipIndicIe = this.tipIndicIeService.getByCnpjEmpresaAndId(informacaoTelaInicial.getCnpjEmpresa(),
                informacaoTelaInicial.getDashboardCliente().getIndicIe().getId());
        if (!tipIndicIe.isPresent()) {
            TipIndicIe indicIe = new TipIndicIe();
            indicIe.setCnpjEmpresa(informacaoTelaInicial.getCnpjEmpresa());
            indicIe.setId(informacaoTelaInicial.getDashboardCliente().getIndicIe().getId());
            indicIe.setTipo(informacaoTelaInicial.getDashboardCliente().getIndicIe().getTipo());
            this.tipIndicIeService.save(indicIe);
        }
    }

    private void saveFinregTribIfApplicable(InformacaoTelaInicial informacaoTelaInicial) {
        Optional<Finregtrib> finregtrib = this.finregtribService.getByCnpjEmpresaAndNumregtrib(informacaoTelaInicial.getCnpjEmpresa(), informacaoTelaInicial.getDashboardCliente().getRegtrib().getNumregtrib());
        if (!finregtrib.isPresent()) {
            Finregtrib regtrib = new Finregtrib();
            regtrib.setCnpjEmpresa(informacaoTelaInicial.getCnpjEmpresa());
            regtrib.setNumregtrib(informacaoTelaInicial.getDashboardCliente().getRegtrib().getNumregtrib());
            regtrib.setNomregtrib(informacaoTelaInicial.getDashboardCliente().getRegtrib().getNomregtrib());
            this.finregtribService.save(regtrib);
        }
    }

}
