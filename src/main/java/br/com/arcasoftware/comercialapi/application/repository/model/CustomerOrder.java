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
}
