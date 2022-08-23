package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "FINREGTRIB")
public class Finregtrib extends BaseEntity {
    private Integer numregtrib;
    private String nomregtrib;
}
