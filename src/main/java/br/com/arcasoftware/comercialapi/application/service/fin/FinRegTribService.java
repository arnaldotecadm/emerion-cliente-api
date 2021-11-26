package br.com.arcasoftware.comercialapi.application.service.fin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arcasoftware.comercialapi.application.repository.fin.FinRegTribRepository;
import br.com.arcasoftwares.model.Finregtrib;

@Service
public class FinRegTribService {

	@Autowired
	private FinRegTribRepository repository;

	public List<Finregtrib> getAllShort() {
		return (List<Finregtrib>) repository.findAll();
	}
}
