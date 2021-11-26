package br.com.arcasoftware.comercialapi.application.service.fin;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.repository.fin.FinCliRepository;
import br.com.arcasoftware.comercialapi.controller.generics.RestBasicService;
import br.com.arcasoftwares.model.Fincli;
import br.com.arcasoftwares.model.dto.FinCliDTO;
import br.com.arcasoftwares.model.dto.IFinCliDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinCliService implements RestBasicService<Fincli, IFinCliDTO> {

	@Autowired
	private FinCliRepository repository;

	@CacheEvict(value = {"getall_fincli", "getall_fincli_dto"})
	public Fincli save(Fincli fincli) {
		return repository.save(fincli);
	}
	
	public Fincli getById(Integer codcli){
		return repository.findById(codcli).orElseThrow(() ->new ValidationException(EnumException.CLIENTE_NAO_ENCONTRADO));
	}
	
	public List<Fincli> getByFilter(FinCliDTO finCliDTO){
		return repository.getByCodcliAndNomcli(finCliDTO.getCodcli(), finCliDTO.getNomcli() != null ? finCliDTO.getNomcli().toUpperCase() : finCliDTO.getNomcli());
	}

	@Cacheable("getall_fincli")
	@Override
	public Page<Fincli> getAllPaged(Pageable page) {
		return this.repository.findAll(page);
	}

	@Cacheable("getall_fincli_dto")
	@Override
	public List<IFinCliDTO> getAllDTO() {
		return this.repository.getAllDTO();
	}

}
