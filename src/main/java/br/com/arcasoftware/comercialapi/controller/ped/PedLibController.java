package br.com.arcasoftware.comercialapi.controller.ped;

import br.com.arcasoftware.comercialapi.application.service.ped.PedLibService;
import br.com.arcasoftwares.model.Pedlib;
import br.com.arcasoftwares.model.dto.PedLibDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/pedlib")
public class PedLibController {

	private final PedLibService service;

	@Autowired
	public PedLibController(PedLibService service){
		this.service = service;
	}

	@GetMapping(value = "all")
	public List<PedLibDTO> getAllPedRes() {
		List<Pedlib> pedlibList = service.getAll();
		List<PedLibDTO> pedDTOlist = new ArrayList<>();
		pedlibList.forEach(res -> pedDTOlist.add(new PedLibDTO(res)));
		return pedDTOlist;
	}

	@GetMapping(value = "numres/{numres}")
	public PedLibDTO getByNumres(@PathVariable("numres") Integer numres) {
		Pedlib p = service.getByNumres(numres);
		return new PedLibDTO(p);
	}
	
	@GetMapping(value = "filter/{pedLibDTO}")
	public List<PedLibDTO> getByFilter(@PathVariable String pedLibDTO) {
		List<Pedlib> pedlibList = service.getByFilter(new Gson().fromJson(pedLibDTO, PedLibDTO.class));
		List<PedLibDTO> pedlibDTOlist = new ArrayList<>();
		pedlibList.forEach(pedlib -> pedlibDTOlist.add(new PedLibDTO(pedlib)));
		return pedlibDTOlist;
	}

}
