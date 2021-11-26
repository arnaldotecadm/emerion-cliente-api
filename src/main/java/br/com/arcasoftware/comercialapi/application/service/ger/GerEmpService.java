package br.com.arcasoftware.comercialapi.application.service.ger;

import br.com.arcasoftware.comercialapi.application.repository.ger.GerEmpRepository;
import br.com.arcasoftwares.model.auxiliar.GerEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerEmpService {

	@Autowired
	private GerEmpRepository repository;

	public List<GerEmp> getAllShort() {
		return (List<GerEmp>) repository.findAll();
	}
}
