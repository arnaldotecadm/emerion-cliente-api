package br.com.arcasoftware.comercialapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class CadastroAuxiliarService {

    @Autowired
    EntityManager manager;
    private Class<?> clazz;
    private String tableName;

    public CadastroAuxiliarService forClass(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public CadastroAuxiliarService withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public <T> List<T> getAll() {
        Query query = this.manager.createNativeQuery("select * from :pTableName", this.clazz);
        query.setParameter("pTableName", tableName);
        return query.getResultList();
    }

}
