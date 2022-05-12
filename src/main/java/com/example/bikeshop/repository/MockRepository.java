package com.example.bikeshop.repository;

import com.example.bikeshop.product.Product;

import java.util.List;

/**
 * This is a mock implementation of the ProductRepository interface.
 */
public class MockRepository implements ProductRepository{

    /**
     * It finds if there is a product in the table with a specific brand and name.
     * @param brand The brand of the product we want to find
     * @param name The name of the product we want to find
     * @return The product found or null, otherwise
     */
    @Override
    public Product findProductByBrandAndName(String brand, String name) {
        return null;
    }

    /**
     * Finds all the products from the product table.
     * @return A List of products
     */
    @Override
    public List<Product> findAll() {
        return null;
    }

    /**
     * Saves a product to the adequate table.
     * @param product The product to be added.
     */
    @Override
    public void save(Product product) {

    }

    /**
     * Determines if a product of a specific id exists in the table.
     * @param id The id of the product we want to perform our search on
     * @return True if a product is found and false otherwise
     */
    @Override
    public boolean existsById(long id) {
        return false;
    }

    /**
     * Deletes a product with a specified id
     * @param id The id of the product we want to delete
     */
    @Override
    public void deleteById(long id) {

    }

    /**
     * Finds a product with a specific id
     * @param id The id of the product we want to perform our search on
     * @return The product found or null if it doesn't exist
     */
    @Override
    public Product findById(long id) {
        return null;
    }
}
