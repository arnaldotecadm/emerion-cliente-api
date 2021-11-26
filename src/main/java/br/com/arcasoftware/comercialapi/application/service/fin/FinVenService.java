package br.com.arcasoftware.comercialapi.application.service.fin;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.fin.FinVenRepository;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftwares.model.Finven;
import br.com.arcasoftwares.model.dto.IFinVenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinVenService implements RestBasicService<Finven, IFinVenDTO> {

    @Autowired
    private FinVenRepository repository;

    public Finven save(Finven finven) {
        return repository.save(finven);
    }

    @Override
    public Page<Finven> getAllPaged(Pageable page) {
        return this.repository.findAll(page);
    }

    @Override
    public Finven getById(Integer codcli) {
        return repository.findById(codcli)
                .orElseThrow(() -> new ValidationException(EnumException.CLIENTE_NAO_ENCONTRADO));
    }

    @Override
    public List<IFinVenDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }

}
