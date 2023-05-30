package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "customer_address",
        indexes = @Index(name = "customer_address_codcli", columnList = "codcli"))
public class CustomerAddress extends BaseEntityV2 {
    private String codcli;
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
