package com.example.bikeshop.repository;

import com.example.bikeshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This class implements the ProductRepository interface
 */
@Repository
public class JPARepository implements ProductRepository {
    /**
     * For this class we need a JPARepository interface which is a lever for getting into the JPARepository,
     * where all kinds of useful methods for communicating with the actual database are implemented.
     */
    private final JPARepositoryInterface jpaRepositoryInterface;

    /**
     * The constructor of the class
     * @param jpaRepositoryInterface The instantiated JPARepositoryInterface object
     */
    @Autowired
    public JPARepository(JPARepositoryInterface jpaRepositoryInterface){
        this.jpaRepositoryInterface = jpaRepositoryInterface;
    }

    /**
     * It finds if there is a product in the table with a specific brand and name.
     * @param brand The brand of the product we want to find
     * @param name The name of the product we want to find
     * @return The product found or null, otherwise
     */
    @Override
    public Product findProductByBrandAndName(String brand, String name) {
        List<Product> optionalProduct = jpaRepositoryInterface.findAll();
        for(Product p : optionalProduct){
            if(p.getBrand().equals(brand) && p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Finds all the products from the product table.
     * @return A List of products
     */
    @Override
    public List<Product> findAll() {
        return jpaRepositoryInterface.findAll();
    }

    /**
     * Saves a product to the adequate table.
     * @param product The product to be added.
     */
    @Override
    public void save(Product product) {
        jpaRepositoryInterface.save(product);
    }

    /**
     * Determines if a product of a specific id exists in the table.
     * @param id The id of the product we want to perform our search on
     * @return True if a product is found and false otherwise
     */
    @Override
    public boolean existsById(long id) {
        Optional<Product> optionalProduct = jpaRepositoryInterface.findById(id);
        return optionalProduct.isPresent();
    }

    /**
     * Deletes a product with a specified id
     * @param id The id of the product we want to delete
     */
    @Override
    public void deleteById(long id) {
        jpaRepositoryInterface.deleteById(id);
    }

    /**
     * Finds a product with a specific id
     * @param id The id of the product we want to perform our search on
     * @return The product found or null if it doesn't exist
     */
    @Override
    public Product findById(long id) {
        Optional<Product> p = jpaRepositoryInterface.findById(id);
        if(p.isEmpty()){
            return null;
        }
        return p.get();
    }
}
