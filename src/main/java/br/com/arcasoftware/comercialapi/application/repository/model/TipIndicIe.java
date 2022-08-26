package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "tip_indic_ie", uniqueConstraints = {@UniqueConstraint(columnNames = {"cnpjEmpresa", "id"})})
public class TipIndicIe extends BaseEntity {
    private String tipo;
}
