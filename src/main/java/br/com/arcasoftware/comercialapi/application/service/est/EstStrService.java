package br.com.arcasoftware.comercialapi.application.service.est;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.est.EstStrRepository;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftwares.model.Eststr;
import br.com.arcasoftwares.model.dto.IEstStrDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstStrService implements RestBasicService<Eststr, IEstStrDTO> {

    @Autowired
    private EstStrRepository repository;

    public Eststr save(Eststr eststr) {
        return repository.save(eststr);
    }

    @Override
    public Page<Eststr> getAllPaged(Pageable page) {
        return this.repository.findAll(page);
    }

    public Eststr getById(Integer codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new ValidationException(EnumException.ESTSTR_NOT_FOUND));
    }

    @Override
    public List<IEstStrDTO> getAllDTO() {
        return repository.getAllDTO();
    }

}
