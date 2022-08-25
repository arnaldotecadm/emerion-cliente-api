package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pedre2")
public class Pedre2 extends BaseEntity {
    private long numres;
}
