package br.com.arcasoftware.comercialapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemComErro {
	private String codClp;
	private String codGru;
	private String codSub;
	private String codPro;
	private String erro;
}