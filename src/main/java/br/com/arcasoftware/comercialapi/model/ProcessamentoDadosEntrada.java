package br.com.arcasoftware.comercialapi.model;

import br.com.arcasoftware.comercialapi.application.service.trigger.pedre2.TipEmpEnum;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
public class ProcessamentoDadosEntrada {
	private Integer numres;
	private String codClp;
	private String codGru;
	private String codSub;
	private String codPro;
	private String ufeEmp;
	private TipEmpEnum tipEmp;
	private int codTfo;
	private String sistema;

	public void setCodGru(String codGru) {
		this.codGru =  new DecimalFormat("000").format(Integer.parseInt(codGru));
	}

	public void setCodSub(String codSub) {
		this.codSub = new DecimalFormat("0000").format(Integer.parseInt(codSub));
	}

	public void setCodPro(String codPro) {
		this.codPro = new DecimalFormat("00000").format(Integer.parseInt(codPro));
	}
}