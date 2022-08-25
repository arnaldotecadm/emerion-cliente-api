package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "finven")
public class Finven extends BaseEntity {
    private long codven;
    private String nomven;
}
