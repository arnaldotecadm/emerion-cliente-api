package br.com.arcasoftware.comercialapi.application.service.ger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arcasoftware.comercialapi.application.repository.ger.GerUfeRepository;
import br.com.arcasoftwares.model.auxiliar.Gerufe;

@Service
public class GerUfeService {

	@Autowired
	private GerUfeRepository repository;

	public List<Gerufe> getAllShort() {
		return (List<Gerufe>) repository.findAll();
	}
}
