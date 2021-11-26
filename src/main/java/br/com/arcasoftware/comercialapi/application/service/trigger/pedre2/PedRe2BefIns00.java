package br.com.arcasoftware.comercialapi.application.service.trigger.pedre2;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.arcasoftware.comercialapi.application.service.trigger.service.TriggerService;
import br.com.arcasoftwares.model.Pedre2;

public class PedRe2BefIns00 {

	private final TriggerService service;
	private final PedRe2CommonMath commonMath;

	@Autowired
	public PedRe2BefIns00(TriggerService service, PedRe2CommonMath commonMath){
		this.service = service;
		this.commonMath = commonMath;
	}

	public void processar(Pedre2 pedre2) throws ValidationException {
		String flag = this.service.getFlgCon() == null ? "" : this.service.getFlgCon().getFlgCon();
		if (!flag.equals("*")) {
			commonMath.insertOperations(pedre2);
		} else {
			throw new ValidationException("EXCEPTION_ERR999");
		}

	}

}
