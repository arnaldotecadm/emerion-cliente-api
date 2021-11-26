package br.com.arcasoftware.comercialapi.application.service.fin;

import br.com.arcasoftware.comercialapi.application.repository.fin.FinCliRepository;
import br.com.arcasoftwares.model.dto.IFinCliDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinCliService {

    private FinCliRepository repository;

    public FinCliService(FinCliRepository repository) {
        this.repository = repository;
    }

    @Cacheable("getall_fincli_dto")

    public List<IFinCliDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }

}
