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
        name = "customer_order",
        indexes = @Index(name = "customer_order_codcli", columnList = "codcli"))
public class CustomerOrder extends BaseEntityV2 {
    private String codcli;
    private String numres;
    private Instant dteres;
    private String sitres;
    private Double totger;
    private Double totipi;
    private Double totsub;
    private Double totdescinc;
    private Double totres;

    private String nomemp;
    private String end1;
    private String end2;
    private String webemp;
    private String emaemp;
    private String nomcli;
    private String endent;
    private String apeven;
    private String cgcemp;
    private String insemp;
    private Instant dtfres;
    private Integer idFrete;
    private String descfrt;
    private String telefone;
    private String emaven;
    private String cgccli;
    private String cidcli;
    private String inscli;
    private String ufecli;
    private String cepcli;
    private String nomtra;
    private String endtra;
    private String cgctra;
    private String cidtra;
    private String fonetra;
    private String ufetra;
    private String ceptra;
    private Double toticm;
    private String obsres;
}
