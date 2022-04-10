package io.github.dvitorsantos.domain.repository;

import io.github.dvitorsantos.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Customers extends JpaRepository<Customer, Integer> {
    List<Customer> findByNameLike(String name);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.requests WHERE c.id = :id")
    Customer findCustomerFetchRequests(@Param("id") Integer id);
}
