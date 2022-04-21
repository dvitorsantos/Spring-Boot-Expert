package io.github.dvitorsantos.rest.controller;

import io.github.dvitorsantos.domain.entity.Customer;
import io.github.dvitorsantos.domain.repository.Customers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    //customers repository
    private Customers repository;

    public CustomerController(Customers repository) { this.repository = repository; }

    @GetMapping( "{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository
                .findById(id)
                .map(customer -> {
                    repository.delete(customer);
                    return customer;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                                 @RequestBody Customer customer) {

        repository
                .findById(id)
                .map(database_customer -> {
                        customer.setId(database_customer.getId());
                        repository.save(customer);
                        return database_customer;
                    })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @GetMapping
    public List<Customer> find(Customer filter) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);
        return repository.findAll(example);
    }
}
