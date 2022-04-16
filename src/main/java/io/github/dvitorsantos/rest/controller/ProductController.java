package io.github.dvitorsantos.rest.controller;

import io.github.dvitorsantos.domain.entity.Product;
import io.github.dvitorsantos.domain.repository.Products;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //products repository
    private Products repository;

    public ProductController(Products repository) {
        this.repository = repository;
    }

    @GetMapping( "{id}")
    public Product getById(@PathVariable("id") Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody Product product) {
        return repository.save(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository
                .findById(id)
                .map(product -> {
                    repository.delete(product);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody Product product) {

        repository
                .findById(id)
                .map(database_product -> {
                    product.setId(database_product.getId());
                    repository.save(product);
                    return database_product;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
    }

    @GetMapping
    public List<Product> find(Product filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);
        return repository.findAll(example);
    }
}
