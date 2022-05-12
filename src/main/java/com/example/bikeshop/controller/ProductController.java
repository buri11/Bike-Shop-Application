package com.example.bikeshop.controller;

import com.example.bikeshop.businesslogic.ProductService;
import com.example.bikeshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller of the application.
 */
@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    /**
     * An instance of a ProductService object which is the layer below the controller
     * and we need this to communicate with it
     */
    private final ProductService productService;

    /**
     * The constructor of the class which instantiates the service layer
     * @param productService Service layer object
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all the products that are in the database, through the Service layer.
     * @return A List of products
     */
    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    /**
     * Adds a new product to the database.
     * @param product The product to be added to the database
     * @return A ResponseEntity which is used for test purposes.
     */
    @PostMapping
    public ResponseEntity<String> registerNewProduct(@RequestBody Product product){
        try{
            productService.addNewProduct(product);
            return new ResponseEntity<String>("Added successfully!", HttpStatus.OK);
        }
        catch(IllegalStateException exception){
            return new ResponseEntity<String>("Could not add!", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a product from the database
     * @param productId The id of the product we want to delete
     * @return A ResponseEntity which is used for test purposes.
     */
    @DeleteMapping(path = "{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") long productId){
        try{
            productService.deleteProduct(productId);
            return new ResponseEntity<String>("Deleted successfully!", HttpStatus.OK);
        }
        catch (IllegalStateException exception){
            return new ResponseEntity<String>("Could not delete item!", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Updates a specific product on any field we want
     * @param productId The id of the product we want to update
     * @param brand The new update brand
     * @param name The updated name
     * @param price The new updated price
     * @param quantity The updated quantity
     */
    @PutMapping(path="{productId}")
    public void updateProduct(
            @PathVariable("productId") long productId,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String name,
            //We set a default value so that we know when the update doesn't include these fields
            //By default they receive null value but int and float can't hold that value, unlike strings
            @RequestParam(required = false, defaultValue = "0") float price,
            @RequestParam(required = false, defaultValue = "-1") int quantity){
        productService.updateProduct(productId, brand, name, price, quantity);
    }


}
