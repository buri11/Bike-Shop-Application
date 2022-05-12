package com.example.bikeshop.repository;

import com.example.bikeshop.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface for the Data access layer
 */
public interface ProductRepository{

    //this query finds any input from the table product that has the same
    //brand and name for a product as the one that we want to add to the table
    //so that we ensure we don't have duplicate entries
    /**
     * It finds if there is a product in the table with a specific brand and name.
     * @param brand The brand of the product we want to find
     * @param name The name of the product we want to find
     * @return The product found or null, otherwise
     */
    Product findProductByBrandAndName(String brand, String name);

    /**
     * Finds all the products from the product table.
     * @return A List of products
     */
    List<Product> findAll();

    /**
     * Saves a product to the adequate table.
     * @param product The product to be added.
     */
    void save(Product product);

    /**
     * Determines if a product of a specific id exists in the table.
     * @param id The id of the product we want to perform our search on
     * @return True if a product is found and false otherwise
     */
    boolean existsById(long id);

    /**
     * Deletes a product with a specified id
     * @param id The id of the product we want to delete
     */
    void deleteById(long id);

    /**
     * Finds a product with a specific id
     * @param id The id of the product we want to perform our search on
     * @return The product found or null if it doesn't exist
     */
    Product findById(long id);

}
