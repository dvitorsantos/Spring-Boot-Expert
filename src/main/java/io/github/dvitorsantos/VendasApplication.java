package io.github.dvitorsantos;

import io.github.dvitorsantos.domain.entity.Customer;
import io.github.dvitorsantos.domain.entity.Request;
import io.github.dvitorsantos.domain.repository.Customers;
import io.github.dvitorsantos.domain.repository.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Value("${application.name}")
    private String applicationName;

    @GetMapping("/hello")
    public String helloWorld() {
        return applicationName;
    }

    @Bean
    public CommandLineRunner init (
            @Autowired Customers customers,
            @Autowired Requests requests) {
        return args -> {
            Customer customer = new Customer();
            customer.setName("Vito");
            customers.save(customer);

            Request request = new Request();
            request.setCustomer(customer);
            request.setDate(LocalDate.now());
            request.setTotal(BigDecimal.valueOf(10));

            requests.save(request);

            Customer customer_fetch_requests = customers.findCustomerFetchRequests(customer.getId());

//            System.out.println(customer_fetch_requests);
//            System.out.println(customer_fetch_requests.getRequests());
            requests.findByCustomer(customer).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
