package br.com.arcasoftware.comercialapi.controller.dto.minimized;

import br.com.arcasoftware.comercialapi.application.service.auxiliar.TipIndicIeService;
import br.com.arcasoftware.comercialapi.application.service.fin.FinAtdService;
import br.com.arcasoftware.comercialapi.application.service.fin.FinPaiService;
import br.com.arcasoftware.comercialapi.application.service.fin.FinRegTribService;
import br.com.arcasoftware.comercialapi.application.service.fin.FinVenService;
import br.com.arcasoftware.comercialapi.application.service.ger.GerUfeService;
import br.com.arcasoftwares.model.Finregtrib;
import br.com.arcasoftwares.model.auxiliar.Gerufe;
import br.com.arcasoftwares.model.auxiliar.Tipindicie;
import br.com.arcasoftwares.model.dto.FinAtdDTO;
import br.com.arcasoftwares.model.dto.FinPaiDTO;
import br.com.arcasoftwares.model.dto.IFinVenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/short")
public class FinShortVersion {

	@Autowired
	private FinVenService finvenService;

	@Autowired
	private FinAtdService finatdService;

	@Autowired
	private FinPaiService finpaiService;

	@Autowired
	private GerUfeService gerUfeService;

	@Autowired
	private FinRegTribService finRegTribService;

	@Autowired
	private TipIndicIeService tipIndicIeService;

	@GetMapping(value = "finven")
	public List<IFinVenDTO> getAllFinven() {
		return finvenService.getAllDTO();
	}

	@GetMapping(value = "finatd")
	public List<FinAtdDTO> getAllFinatd() {
		return finatdService.getAllShort();
	}

	@GetMapping(value = "finpai")
	public List<FinPaiDTO> getAllFinpai() {
		return finpaiService.getAllShort();
	}

	@GetMapping(value = "gerufe")
	public List<Gerufe> getAllGerUfe() {
		return gerUfeService.getAllShort();
	}

	@GetMapping(value = "finregtrib")
	public List<Finregtrib> getAllFinRegTrib() {
		return finRegTribService.getAllShort();
	}

	@GetMapping(value = "tipindicie")
	public List<Tipindicie> getAllTipIndicIe() {
		return tipIndicIeService.getAllShort();
	}
}
