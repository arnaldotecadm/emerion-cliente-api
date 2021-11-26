package br.com.arcasoftware.comercialapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParametroAprovarPedido {
    private Integer numres;
    private String sitres;
    private String obs;
}
