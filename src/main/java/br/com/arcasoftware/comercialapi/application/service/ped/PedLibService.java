package br.com.arcasoftware.comercialapi.application.service.ped;

import br.com.arcasoftware.comercialapi.application.repository.ped.PedLibRepository;
import br.com.arcasoftwares.model.Pedlib;
import br.com.arcasoftwares.model.dto.PedLibDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedLibService {

	@Autowired
	private PedLibRepository repository;

	public List<Pedlib> getAll() {
		return (List<Pedlib>) repository.findAll();
	}

	public Pedlib getByNumres(Integer numres) {
		return repository.getByNumres(numres);
	}

	public List<Pedlib> getByFilter(PedLibDTO pedLibDTO) {
		return repository.findByFilters(pedLibDTO.getNumres(), pedLibDTO.getSitlib());
	}
	
}
