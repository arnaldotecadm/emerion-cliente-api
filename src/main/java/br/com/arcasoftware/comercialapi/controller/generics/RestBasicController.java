package br.com.arcasoftware.comercialapi.controller.generics;

import br.com.emerion.model.generics.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class RestBasicController<T extends BaseEntity, I> {

    final RestBasicService<T, I> basicService;

    public RestBasicController(RestBasicService<T, I> basicService) {
        this.basicService = basicService;
    }

    /**
     * @param page responsible for changing the default paging behaviour
     * @return pageable list of results
     */
    @GetMapping(value = {"full"})
    public Page<T> getAllFull(@PageableDefault(size = 50) Pageable page) {
        return this.basicService.getAllPaged(page);
    }

    /**
     * @return Return a list of all DTOs
     */
    @GetMapping(value = {"", "all"})
    public List<I> getAllDTO() {
        return this.basicService.getAllDTO();
    }

    @GetMapping(value = "{id}")
    public T getById(@PathVariable("id") Integer id) {
        return basicService.getById(id);
    }

    /**
     * create a resource
     *
     * @param t -> generic type to be returned
     * @return Response entity containing the resource location and the resource it self
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<T> save(@RequestBody T t) {
        T saved = basicService.save(t);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}
