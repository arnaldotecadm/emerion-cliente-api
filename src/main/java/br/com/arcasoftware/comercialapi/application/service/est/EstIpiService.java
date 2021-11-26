package br.com.arcasoftware.comercialapi.application.service.est;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.est.EstIpiRepository;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftwares.model.Estipi;
import br.com.arcasoftwares.model.dto.IEstIpiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstIpiService implements RestBasicService<Estipi, IEstIpiDTO> {

    private final EstIpiRepository repository;

	@Autowired
    public EstIpiService(EstIpiRepository repository){
    	this.repository = repository;
	}
    public Estipi save(Estipi estipi) {
        return repository.save(estipi);
    }

    @Override
    public Page<Estipi> getAllPaged(Pageable page) {
        return this.repository.findAll(page);
    }

    public Estipi getById(Integer codcli) {
        return repository.findById(codcli)
                .orElseThrow(() -> new ValidationException(EnumException.CLIENTE_NAO_ENCONTRADO));
    }

    @Override
    public List<IEstIpiDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }

    public Estipi getByTipoAndCodigo(String tipoIpi, String codIpi) {
        return repository.getByIdentificador(tipoIpi, codIpi)
                .orElseThrow(() -> new ValidationException(EnumException.ESTIPI_NOT_FOUND));
    }

}
