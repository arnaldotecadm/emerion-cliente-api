package br.com.arcasoftware.comercialapi.application.repository.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {
    private String cnpjEmpresa;
    private long codcli;
    private String tipo;
    private String cefcli;
    private String tefcli;
    private String enfcli;
    private String nrfcli;
    private String rffcli;
    private String bafcli;
    private String cifcli;
    private String uffcli;
    private String pt1cli;
    private String fo1cli;
    private String cofcli;
    private String pc1cli;
    private String fc1cli;
}
