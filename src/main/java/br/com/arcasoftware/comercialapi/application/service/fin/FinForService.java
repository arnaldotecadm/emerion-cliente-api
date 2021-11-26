package br.com.arcasoftware.comercialapi.application.service.fin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arcasoftware.comercialapi.application.repository.fin.FinForRepository;
import br.com.arcasoftwares.model.interfaces.FinForInterface;

@Service
public class FinForService {

	@Autowired
	private FinForRepository repository;

	public List<FinForInterface> getByCodFor(String codFor) {
		return repository.getByCodFor(codFor);
	}
}
