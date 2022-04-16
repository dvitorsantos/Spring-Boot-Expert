package io.github.dvitorsantos;

import io.github.dvitorsantos.domain.entity.Customer;
import io.github.dvitorsantos.domain.repository.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner (@Autowired Customers customers) {
        return args -> {
            Customer customer = new Customer(null, "nome teste");
            customers.save(customer);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
