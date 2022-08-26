package br.com.arcasoftware.comercialapi.application.repository.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DashboardEndereco {
    private String tipo;
    private String cep;
}
