package com.example.bikeshop.controller;

import com.example.bikeshop.businesslogic.ProductService;
import com.example.bikeshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public void registerNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") long productId){
        productService.deleteStudent(productId);
    }

    @PutMapping(path="{productId}")
    public void updateProduct(
            @PathVariable("productId") long productId,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String name,
            //We set a default value so that we know when the update doesn't include these fields
            //by default they receive null valuea but int and float can't hold that value, unlike strings
            @RequestParam(required = false, defaultValue = "0") float price,
            @RequestParam(required = false, defaultValue = "-1") int quantity){
        productService.updateProduct(productId, brand, name, price, quantity);
    }


}
