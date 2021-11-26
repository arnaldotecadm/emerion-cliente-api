package br.com.arcasoftware.comercialapi.controller.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/***
 * T stands for TYPE to be returned
 * I stands for Interface to be returned
 * @param <T>
 * @param <I>
 */
public interface RestBasicService<T, I> {

    Page<T> getAllPaged(Pageable page);

    T getById(Integer id);

    List<I> getAllDTO();

    T save(T t);
}
