package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "fintra")
public class Fintra extends BaseEntity {
    private long codtra;
    private String nomtra;
    private String cgctra;
    private String cidtra;
    private String ceptra;
    private String prttra;
    private String fontra;
    private String tentra;
    private String endtra;
    private String numtra;
    private String baitra;
    private String sigufe;
}
