package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "estmrc")
public class Estmrc extends BaseEntity {
    private Integer codmrc;
    private String nommrc;
}
