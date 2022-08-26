package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "FINCDE", uniqueConstraints = {@UniqueConstraint(columnNames = {"codemp", "dtecde", "seqcde"})})
public class Fincde extends BaseEntity {
    private int codemp;
    private String dtecde;
    private int seqcde;

    private long codcli;
    private double usacde;
    private double valcde;
    private Double sldcde;

    private String dteped;
    private String obscde;
    private String sitcde;
}
