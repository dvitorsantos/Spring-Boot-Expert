package io.github.dvitorsantos.domain.repository;

import io.github.dvitorsantos.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Products extends JpaRepository<Product, Integer> {

}
