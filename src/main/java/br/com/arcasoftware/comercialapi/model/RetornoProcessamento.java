package br.com.arcasoftware.comercialapi.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoProcessamento {
	private String tempoGasto;
	private Integer totalItems = 0;
	private List<ItemComErro> listaItemErro = new ArrayList<>();
	private List<ProcessamentoDadosSaida> itemsProcessados = new ArrayList<>();
}
