package br.com.arcasoftware.comercialapi.mapper;

import br.com.arcasoftware.comercialapi.application.repository.model.ClienteDocument;
import br.com.arcasoftware.comercialapi.application.repository.model.dto.ClienteData;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClienteDataMapper {
    public ClienteData clienteDocumentToCLienteData(ClienteDocument clienteDocument) {
        ClienteData clienteData = new ClienteData();
        BeanUtils.copyProperties(clienteDocument, clienteData);
        return clienteData;
    }

    public ClienteDocument clienteDataToClienteDocument(ClienteData clienteData) {
        ClienteDocument clienteDocument = new ClienteDocument();
        BeanUtils.copyProperties(clienteData, clienteDocument);
        return clienteDocument;
    }
}
