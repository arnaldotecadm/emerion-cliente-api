package br.com.arcasoftware.comercialapi.application.service.est;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.est.EstUfeRepository;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftwares.model.Estufe;
import br.com.arcasoftwares.model.dto.IEstUfeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstUfeService implements RestBasicService<Estufe, IEstUfeDTO> {

    @Autowired
    private EstUfeRepository repository;

    public Estufe save(Estufe estufe) {
        return repository.save(estufe);
    }

    @Override
    public Page<Estufe> getAllPaged(Pageable page) {
        return this.repository.findAll(page);
    }

    public Estufe getById(Integer codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new ValidationException(EnumException.ESTSTR_NOT_FOUND));
    }

    @Override
    public List<IEstUfeDTO> getAllDTO() {
        return repository.getAllDTO();
    }


    public List<IEstUfeDTO> getAllByTipoAndCodigo(String tipo, String codigo) {
        return this.repository.getAllByTipoAndCodigo(tipo, codigo);
    }
}
