package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "finatd", uniqueConstraints = {@UniqueConstraint(columnNames = {"cnpjEmpresa", "codatd"})})
public class Finatd extends BaseEntity {
    private long codatd;
    private String nomatd;
}
