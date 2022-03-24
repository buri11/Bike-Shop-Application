package com.example.bikeshop.repository;

import com.example.bikeshop.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //this query finds any input from the table product that has the same
    //brand and name for a product as the one that we want to add to the table
    //so that we ensure we don't have duplicate entries

    Optional<Product>findProductByBrandAndName(String brand, String name);
}
