package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "pedre2", uniqueConstraints = {@UniqueConstraint(columnNames = {"cnpjEmpresa", "numres", "codgru", "codsub", "codpro"})})
public class Pedre2 extends BaseEntity {
    private long numres;
    private Integer codemp;
    private String dteres;
    private Integer seqre2;
    private String flgval;
    private String flgpac;
    private String flglib;
    private String clsipi;
    private String codcfo;
    private String codund;
    private Double vlure2;
    private Double dscre2;
    private Double dsrre2;
    private String obsre2;
    private Double totseg;
    private Double totoutdesp;
    private Integer numpedcompra;
    private Integer numitemcompra;
    private Double totge2;
    private Double totitetrb;
    private Integer nrore2;
    private Integer codclp;

    private String codgru;
    private String codsub;
    private String codpro;

    private String desre2;
    private BigDecimal qtpre2;
    private BigDecimal vlqre2;
    private BigDecimal icmre2;
    private BigDecimal totre2;
    private BigDecimal totren;
    private BigDecimal basicm;
    private BigDecimal toticm;
    private String codst1;
    private String codst2;
    private BigDecimal redicm;
    private BigDecimal basipi;
    private BigDecimal ipire2;
    private String cstipi;
    private BigDecimal totdsr;
    private BigDecimal totfrt;
    private BigDecimal bassub;
    private BigDecimal icmsub;
    private BigDecimal mrgsub;
    private BigDecimal redsub;
    private BigDecimal baspis;
    private BigDecimal aliqpis;
    private BigDecimal totpis;
    private String cstpis;
    private BigDecimal bascof;
    private BigDecimal aliqcof;
    private BigDecimal totcof;
    private String cstcof;

    private Double totipi;
    private Double totsub;
}
