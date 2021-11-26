package br.com.arcasoftware.comercialapi.application.service.est;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.est.EstIcmRepository;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftwares.model.Esticm;
import br.com.arcasoftwares.model.dto.EstIcmDTO;
import br.com.arcasoftwares.model.dto.IEstIcmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstIcmService implements RestBasicService<Esticm, IEstIcmDTO> {

    @Autowired
    private EstIcmRepository repository;

    public Esticm save(Esticm esticm) {
        return repository.save(esticm);
    }

    @Override
    public Page<Esticm> getAllPaged(Pageable page) {
        return this.repository.findAll(page);
    }

    public Esticm getById(Integer codcli) {
        return repository.findById(codcli)
                .orElseThrow(() -> new ValidationException(EnumException.CLIENTE_NAO_ENCONTRADO));
    }

    @Override
    public List<IEstIcmDTO> getAllDTO() {
        return repository.getAllDTO();
    }

    public Esticm getById(EstIcmDTO estIcmDTO) {
        return repository
                .getByIdentificador(estIcmDTO.getTipicm(), estIcmDTO.getCodicm(), estIcmDTO.getUfeemitente(),
                        estIcmDTO.getCodregtrib())
                .orElseThrow(() -> new ValidationException(EnumException.ESTICM_NOT_FOUND));
    }

    public List<Esticm> getByTipoAndCodigo(String tipoIcms, String codIcms) {
        return this.repository.getByTipoCodigo(tipoIcms, codIcms);
    }
}
