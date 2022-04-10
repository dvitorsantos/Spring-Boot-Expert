package io.github.dvitorsantos.domain.repository;

import io.github.dvitorsantos.domain.entity.Customer;
import io.github.dvitorsantos.domain.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Requests extends JpaRepository<Request, Integer> {
    List<Request> findByCustomer(Customer customer);
}
