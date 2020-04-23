package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.dto.request.CategoryRequest;
import br.com.cast.avaliacao.dto.response.CategoryResponse;
import br.com.cast.avaliacao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<CategoryResponse>> findAll() {

        final List<CategoryResponse> categoryList = categoryService.findAll();

        return ResponseEntity.ok(categoryList);
    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<CategoryResponse> findById(@PathVariable("id") final Long id) {

        final CategoryResponse categoryResponse = categoryService.findById(id);

        return ResponseEntity.ok(categoryResponse);
    }


    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<CategoryResponse>> findById(@RequestParam(required = false) String description) {

        final List<CategoryResponse> categoryResponseList = categoryService.findByDescription(description);

        return ResponseEntity.ok(categoryResponseList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<URI> create(@Valid @RequestBody final CategoryRequest categoryRequest) {

        final Long id = categoryService.create(categoryRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Void> update(@PathVariable("id") final Long id,
                                       @Valid @RequestBody final CategoryRequest categoryRequest) {

        categoryService.update(id, categoryRequest);

        return ResponseEntity.noContent().build();
    }
}
