package com.example.bikeshop.businesslogic;

import com.example.bikeshop.product.Product;
import com.example.bikeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }


    public void addNewProduct(Product product) {
        Optional<Product> productOptional = productRepository.findProductByBrandAndName(product.getBrand(), product.getName());
        if(productOptional.isPresent()){
            throw new IllegalStateException("Brand and name combination taken");
        }
        productRepository.save(product);
    }

    public void deleteStudent(long productId) {
        boolean productExists = productRepository.existsById(productId);
        if(!productExists){
            throw new IllegalStateException("Product with ID " + productId + " doesn't exist");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(long productId, String brand, String name, float price, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalStateException("Product with ID " + productId + " doesn't exist"));

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


