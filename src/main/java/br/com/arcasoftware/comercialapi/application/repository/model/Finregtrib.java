package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "FINREGTRIB", uniqueConstraints = {@UniqueConstraint(columnNames = {"cnpjEmpresa", "numregtrib"})})
public class Finregtrib extends BaseEntity {
    private Integer numregtrib;
    private String nomregtrib;
}
