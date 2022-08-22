package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FinCliRepository;
import br.com.arcasoftware.comercialapi.application.repository.model.ClienteData;
import br.com.arcasoftware.comercialapi.application.repository.model.ClienteDocument;
import br.com.arcasoftware.comercialapi.mapper.ClienteDataMapper;
import br.com.arcasoftware.comercialapi.model.dto.IFinCliDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinCliService {

    private FinCliRepository repository;
    private final ClienteDataMapper clienteDataMapper;

    public FinCliService(FinCliRepository repository, ClienteDataMapper clienteDataMapper) {
        this.repository = repository;
        this.clienteDataMapper = clienteDataMapper;
    }

    public List<IFinCliDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }

    public ClienteDocument save(ClienteData clienteData) {
        ClienteDocument document = this.clienteDataMapper.clienteDataToClienteDocument(clienteData);
        return this.repository.save(document);
    }

}
