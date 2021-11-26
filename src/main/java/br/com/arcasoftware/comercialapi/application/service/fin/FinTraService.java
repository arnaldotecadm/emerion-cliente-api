package br.com.arcasoftware.comercialapi.application.service.fin;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.fin.FinTraRepository;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftwares.model.Fintra;
import br.com.arcasoftwares.model.dto.IFinTraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinTraService implements RestBasicService<Fintra, IFinTraDTO> {

    @Autowired
    private FinTraRepository repository;

    public Fintra save(Fintra fintra) {
        return repository.save(fintra);
    }

    @Override
    public Page<Fintra> getAllPaged(Pageable page) {
        return this.repository.findAll(page);
    }

    @Override
    public Fintra getById(Integer codcli) {
        return repository.findById(codcli)
                .orElseThrow(() -> new ValidationException(EnumException.CLIENTE_NAO_ENCONTRADO));
    }

    @Override
    public List<IFinTraDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }
}
