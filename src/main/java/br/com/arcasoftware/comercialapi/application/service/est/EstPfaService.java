package br.com.arcasoftware.comercialapi.application.service.est;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.est.EstPfaRepository;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftwares.model.Estpfa;
import br.com.arcasoftwares.model.dto.EstPfaDTO;
import br.com.arcasoftwares.model.dto.IEstPfaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstPfaService implements RestBasicService<Estpfa, IEstPfaDTO> {

    @Autowired
    private EstPfaRepository repository;

    public Estpfa save(Estpfa estpfa) {
        return repository.save(estpfa);
    }

    @Override
    public Page<Estpfa> getAllPaged(Pageable page) {
        return this.repository.findAll(page);
    }

    public Estpfa getById(Integer codcli) {
        return repository.findById(codcli)
                .orElseThrow(() -> new ValidationException(EnumException.CLIENTE_NAO_ENCONTRADO));
    }

    @Override
    public List<IEstPfaDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }

    public Estpfa getById(EstPfaDTO estPfaDTO) {
        return repository.getByIdentificador(estPfaDTO.getTippfa(), estPfaDTO.getCodpfa()).orElseThrow(() -> new ValidationException(EnumException.ESTPFA_NOT_FOUND));
    }

}
