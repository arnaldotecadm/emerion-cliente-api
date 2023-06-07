package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "customer_order_detail",
        indexes = @Index(name = "customer_order_detail_codcli", columnList = "codcli"))
public class CustomerOrderDetail extends BaseEntityV2 {
    private UUID customerOrder;
    private String numres;
    private String codcli;
    private String desre2;
    private Double vlqre2;
    private Double qtpre2;
    private Double totre2;

    private String produto;
    private Double totge2;
    private Double totdsr;
    private Double totdespesa;
    private Double totsub;
    private Double mrgsub;
    private Double totipi;
    private Double ipire2;
    private Double toticm;
    private Double icmre2;
    private Double dscre2;
    private Double vlure2;
    private String codund;
    private String codcfo;
    private String cst;
    private String clsipi;
}
