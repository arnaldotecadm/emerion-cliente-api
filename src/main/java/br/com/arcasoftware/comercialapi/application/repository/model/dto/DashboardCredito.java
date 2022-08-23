package br.com.arcasoftware.comercialapi.application.repository.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DashboardCredito {
    private int codemp;
    private String dtecde;
    private int seqcde;
    private Integer sedcde;
    private double usacde;
    private double valcde;
}
