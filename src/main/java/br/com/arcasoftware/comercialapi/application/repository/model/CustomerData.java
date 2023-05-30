package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "customer_data",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"cnpjEmpresa", "codcli"})},
        indexes = @Index(name = "customer_data_codcli", columnList = "codcli"))
public class CustomerData extends BaseEntityV2 {
    private String codcli;
    private String nomcli;
    private String apecli;
    private String cgccli;
    private String inscli;
    private String nomregtrib;
    private String clidev;
    private String cifcli;
    private String uffcli;
    private String codven;
    private String cnae;
    private String indicEstrangeiro;
    private String tipoIndicacaoIe;
    private String inscMunicipal;
    private String obscli;
    private String indicIe;
}
