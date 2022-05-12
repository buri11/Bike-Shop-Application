package com.example.bikeshop.businesslogic;

import com.example.bikeshop.product.Product;
import com.example.bikeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * This is the service layer of the application
 */
@Service
public class ProductService {

    /**
     * This is a ProductRepository object which we need for communicating with the layer below,
     * which is the data access layer.
     */
    private final ProductRepository productRepository;

    /**
     * The constructor of the class, where we instantiate ProductRepository object
     * @param productRepository Data layer object
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * This gets all the products from the database.
     * @return A List of products
     */
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    /**
     * Adds a new product to the database.
     * @param product The product to be added to the database
     */
    public void addNewProduct(Product product) {
        Product productOptional = productRepository.findProductByBrandAndName(product.getBrand(), product.getName());
        if(productOptional != null){
            throw new IllegalStateException("Brand and name combination taken");
        }
        productRepository.save(product);
    }

    /**
     * Deletes a product from the database.
     * @param productId The id of the product we want to delete.
     */
    public void deleteProduct(long productId) throws IllegalStateException{
        boolean productExists = productRepository.existsById(productId);
        if(!productExists){
            throw new IllegalStateException("Product with ID " + productId + " doesn't exist");
        }
        productRepository.deleteById(productId);
    }

    /**
     * Updates a specific product from the database, on any field.
     * @param productId The id of the product we want to update
     * @param brand The new update brand
     * @param name The updated name
     * @param price The new updated price
     * @param quantity The updated quantity
     */
    @Transactional
    public void updateProduct(long productId, String brand, String name, float price, int quantity) {
        Product product = productRepository.findById(productId);
        if(product == null){
            throw new IllegalStateException("Product with ID " + productId + " doesn't exist");
        }

        if(brand != null && brand.length() > 0 && !Objects.equals(brand, product.getBrand())){
            product.setBrand(brand);
        }

        if(name != null && name.length() > 0 && !Objects.equals(name, product.getName())){
            product.setName(name);
        }

        if(price != 0 && !Objects.equals(price, product.getPrice())){
            product.setPrice(price);
        }

        if(quantity >= 0 && !Objects.equals(quantity, product.getQuantity())){
            product.setQuantity(quantity);
        }

    }
}


