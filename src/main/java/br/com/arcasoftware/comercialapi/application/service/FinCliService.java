package br.com.arcasoftware.comercialapi.application.service;

import br.com.arcasoftware.comercialapi.application.repository.FinCliRepository;
import br.com.arcasoftware.comercialapi.model.dto.IFinCliDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinCliService {

    private FinCliRepository repository;

    public FinCliService(FinCliRepository repository) {
        this.repository = repository;
    }

    public List<IFinCliDTO> getAllDTO() {
        return this.repository.getAllDTO();
    }

}
