package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tip_indic_ie")
public class TipIndicIe extends BaseEntity {
    private Integer id;
    private String tipo;
}
