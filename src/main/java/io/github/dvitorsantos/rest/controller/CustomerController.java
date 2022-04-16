package io.github.dvitorsantos.rest.controller;

import io.github.dvitorsantos.domain.entity.Customer;
import io.github.dvitorsantos.domain.repository.Customers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    //customers repository
    private Customers customers;

    public CustomerController(Customers customers) {
        this.customers = customers;
    }

    @GetMapping( "/{id}")
    @ResponseBody
    public ResponseEntity getCustomerById(@PathVariable("id") Integer id) {
        Optional<Customer> customer = customers.findById(id);

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity save(@RequestBody Customer customer) {
        Customer saved_customer = customers.save(customer);
        return ResponseEntity.ok(saved_customer);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Customer> saved_customer = customers.findById(id);

        if (saved_customer.isPresent()) {
            customers.delete(saved_customer.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Customer customer) {
        Optional<Customer> saved_customer = customers.findById(id);

        if (saved_customer.isPresent()) {
            customer.setId(saved_customer.get().getId());
            customers.save(customer);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity find(Customer filter) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);

        List<Customer> result = customers.findAll(example);

        return ResponseEntity.ok(result);
    }
}
