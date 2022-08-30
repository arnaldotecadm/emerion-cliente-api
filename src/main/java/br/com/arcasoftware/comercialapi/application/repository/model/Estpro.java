package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "estpro", uniqueConstraints = {@UniqueConstraint(columnNames = {"cnpjEmpresa", "codclp", "codgru","codsub","codpro"})})
public class Estpro extends BaseEntity {
    private Integer codclp;
    private String codgru;
    private String codsub;
    private String codpro;
    private Double qtdemb;
    private String refpro;
    private String locpro;
    private Integer codmrc;
}
