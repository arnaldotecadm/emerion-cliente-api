package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "customer_credit",
        indexes = @Index(name = "customer_credit_codcli", columnList = "codcli"))
public class CustomerCredit extends BaseEntityV2 {
    private String codcli;
    private String seqcde;
    private Instant dtecde;
    private String tipo;
    private Double valor;

    private Double usacde;
    private Double valcde;
    private Double sldcde;

    private String dteped;
    private String obscde;
    private String sitcde;
}
