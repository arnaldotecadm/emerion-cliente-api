package br.com.arcasoftware.comercialapi.application.service.fin;

import br.com.arcasoftware.comercialapi.application.repository.fin.FinPaiRepository;
import br.com.arcasoftwares.model.dto.FinPaiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinPaiService {

	@Autowired
	private FinPaiRepository repository;

	public List<FinPaiDTO> getAllShort() {
		return repository.getAllShort();
	}
}
