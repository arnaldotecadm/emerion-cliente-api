package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "FINCDE")
public class Fincde extends BaseEntity {
    private int codemp;
    private String dtecde;
    private int seqcde;

    private long codcli;
    private double usacde;
    private double valcde;
}
