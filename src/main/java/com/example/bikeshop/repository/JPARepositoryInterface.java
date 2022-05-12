package com.example.bikeshop.repository;

import com.example.bikeshop.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the JPA Repository interface which binds our application to JPA
 */
public interface JPARepositoryInterface extends JpaRepository<Product, Long> {
}
