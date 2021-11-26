package br.com.arcasoftware.comercialapi.application.service.fin;

import br.com.arcasoftware.comercialapi.application.repository.fin.FinAtdRepository;
import br.com.arcasoftwares.model.dto.FinAtdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinAtdService {

	@Autowired
	private FinAtdRepository repository;

	public List<FinAtdDTO> getAllShort() {
		return repository.getAllShort();
	}
}
