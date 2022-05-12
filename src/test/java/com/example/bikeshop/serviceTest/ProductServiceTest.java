package com.example.bikeshop.serviceTest;

import com.example.bikeshop.businesslogic.ProductService;
import com.example.bikeshop.product.Product;
import com.example.bikeshop.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

/**
 *
 */
@SpringBootTest
public class ProductServiceTest {
    /**
     *
     */
    @Mock
    private ProductRepository productRepo;
    /**
     *
     */
    @InjectMocks
    private ProductService productService;

    /**
     *
     */
    @Test
    public void getProductsTest(){
        Product prod1 = new Product(1, "CrankBrothers", "Pedals", 5, (float) 54.59);
        Product prod2 = new Product(2, "FatBar", "Handlebar", 2, (float) 104.69);
        Product prod3 = new Product(3, "ESI", "Grip", 6, (float) 12.76);
        List<Product> prods = new ArrayList<>();
        prods.add(prod1);
        prods.add(prod2);
        prods.add(prod3);

        when(productRepo.findAll()).thenReturn(prods);
        Assertions.assertEquals(prods, productService.getProducts());
        Assertions.assertEquals(productService.getProducts().size(), 3);
        assertThat(productService.getProducts().get(0)).isNotEqualTo(prod3);
    }

    /**
     *
     */
    @Test
    public void validationsTest(){
        Product prod1 = new Product(1, "CrankBrothers", "Pedals", 5, (float) 54.59);
        Product prod2 = new Product(2, "FatBar", "Handlebar", 2, (float) 104.69);
        Product prod3 = new Product(3, "ESI", "Grip", 6, (float) 12.76);
        List<Product> prods = new ArrayList<>();
        prods.add(prod1);
        prods.add(prod2);
        prods.add(prod3);

        when(productRepo.existsById(1)).thenReturn(true);
        when(productRepo.existsById(3)).thenReturn(true);
        when(productRepo.existsById(5)).thenReturn(false);
        when(productRepo.findById(2)).thenReturn(prod2);
        when(productRepo.findById(6)).thenReturn(null);

        Assertions.assertThrows(IllegalStateException.class, () -> productService.deleteProduct(5));
        Assertions.assertThrows(IllegalStateException.class, () -> productService.updateProduct(6, null, null, 0, 0));
    }
}
