package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "finven", uniqueConstraints = {@UniqueConstraint(columnNames = {"cnpjEmpresa", "codven"})})
public class Finven extends BaseEntity {
    private long codven;
    private String nomven;
}
