package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "finatd")
public class Finatd extends BaseEntity {
    private long codatd;
    private String nomatd;
}
