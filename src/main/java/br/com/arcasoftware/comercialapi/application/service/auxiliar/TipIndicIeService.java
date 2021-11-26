package br.com.arcasoftware.comercialapi.application.service.auxiliar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arcasoftware.comercialapi.application.repository.auxiliar.TipIndicIeRepository;
import br.com.arcasoftwares.model.auxiliar.Tipindicie;

@Service
public class TipIndicIeService {

	@Autowired
	private TipIndicIeRepository repository;

	public List<Tipindicie> getAllShort() {
		return (List<Tipindicie>) repository.findAll();
	}
}
