package br.com.arcasoftware.comercialapi.controller.generics;

import br.com.arcasoftware.comercialapi.application.enums.EnumException;
import br.com.arcasoftware.comercialapi.application.exception.ValidationException;
import br.com.arcasoftware.comercialapi.application.service.CadastroAuxiliarService;
import br.com.emerion.model.annotation.CadastroAuxiliar;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/auxiliar")
@CrossOrigin("*")
public class CadastroAuxiliarController {

    @Autowired
    CadastroAuxiliarService service;

    @GetMapping("{tableName}")
    public List<Object> getAll(@PathVariable String tableName) {
        this.service.forClass(getClassFromName(tableName)).withTableName(tableName);
        return this.service.getAll();
    }

    private Class<? extends Object> getClassFromName(String tableName){
        Reflections reflections = new Reflections("br.com.arcasoftwares.model");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(CadastroAuxiliar.class);

        for(Class<?extends Object> clazz : typesAnnotatedWith){
            Optional<Annotation> cadastroAuxiliar = Arrays.stream(clazz.getAnnotations()).filter(CadastroAuxiliar.class::isInstance).findFirst();

            if(!cadastroAuxiliar.isPresent()){
                throw new ValidationException(EnumException.CLASS_NOT_IMPLEMENTED);
            }

            CadastroAuxiliar annotation = (CadastroAuxiliar) cadastroAuxiliar.get();

            String value = annotation.value();
            if(value.equalsIgnoreCase(tableName)){
                return clazz;
            }
        }
        throw new ValidationException(EnumException.CLASS_NOT_IMPLEMENTED);
    }
}
