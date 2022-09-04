package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "geremp")
public class Geremp extends BaseEntity {
    private long codemp;
    private String nomemp;
    private String cgcemp;
    private String insemp;
    private String tenemp;
    private String endemp;
    private String numemp;
    private String baiemp;
    private String cidemp;
    private String sigufe;
    private String cepemp;
    private String prtemp;
    private String fonemp;
    private String emaemp;
    private String webemp;
}
