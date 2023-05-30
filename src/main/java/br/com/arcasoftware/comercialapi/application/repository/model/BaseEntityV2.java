package br.com.arcasoftware.comercialapi.application.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Setter
@Getter
public class BaseEntityV2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;
    protected String cnpjEmpresa;
}
