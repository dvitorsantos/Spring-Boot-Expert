package io.github.dvitorsantos.domain.repository;

import io.github.dvitorsantos.domain.entity.Product_Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRequestDAO extends JpaRepository<Product_Request, Integer> {
}
