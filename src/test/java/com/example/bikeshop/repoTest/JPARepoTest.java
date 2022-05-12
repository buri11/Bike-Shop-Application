package com.example.bikeshop.repoTest;

import com.example.bikeshop.product.Product;
import com.example.bikeshop.repository.JPARepository;
import com.example.bikeshop.repository.JPARepositoryInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Test class for the JPARepository class.
 */
@SpringBootTest
public class JPARepoTest {
    /**
     * This is a mock object of the class we want to test.
     */
    @InjectMocks
    private JPARepository jpaRepository;

    /**
     * This is a mock JPARepositoryInterface interface.<br>
     * We need this because the methods of the JPARepository calls methods from this interface.
     */
    @Mock
    private JPARepositoryInterface jpaRepositoryInterface;

    /**
     * This is an auxiliary list of products that helps in testing different functionalities.
     */
    private List<Product> prods;

    /**
     * Creates a list of products to be used in the test below.
     * @return The equivalent of a table, put in an ArrayList
     */
    private List<Product> setupProducts(){
        Product prod1 = new Product(1, "Pedals", "CrankBrothers", 5, (float) 54.59);
        Product prod2 = new Product(2, "Handlebar", "FatBar", 2, (float) 104.69);
        Product prod3 = new Product(3, "Grip", "ESI", 6, (float) 12.76);
        List<Product> products = new ArrayList<>();
        products.add(prod1);
        products.add(prod2);
        products.add(prod3);
        return products;
    }

    /**
     * Tests the method findProductByBrandAndName() from the JPARepository class.<br>
     * It searches throughout the list returned by the findAll() method for products with the brand and name provided.
     */
    @Test
    public void findProductByBrandAndNameTest(){
        prods = setupProducts();

        when(jpaRepositoryInterface.findAll()).thenReturn(prods);
        Assertions.assertEquals(prods.get(1), jpaRepository.findProductByBrandAndName("FatBar", "Handlebar"));
        Assertions.assertEquals(prods.get(2), jpaRepository.findProductByBrandAndName("ESI", "Grip"));
        Assertions.assertNotEquals(prods.get(0), jpaRepository.findProductByBrandAndName("Pedals", "CrankBrothers"));
    }

    /**
     *Tests the method existsById() which returns a boolean value according to the result of the search made by
     * the findById() method.
     */
    @Test
    public void existsByIdTest(){
        prods = setupProducts();

        when(jpaRepositoryInterface.findById(1L)).thenReturn(Optional.ofNullable(prods.get(0)));
        when(jpaRepositoryInterface.findById(2L)).thenReturn(Optional.ofNullable(prods.get(1)));
        when(jpaRepositoryInterface.findById(3L)).thenReturn(Optional.ofNullable(prods.get(2)));

        Assertions.assertTrue(jpaRepository.existsById(1));
        Assertions.assertTrue(jpaRepository.existsById(3));
        Assertions.assertFalse(jpaRepository.existsById(6));
    }

    /**
     *This method tests the findById() method from the JPARepository class and ensures that
     * the product returned has the correct id.
     */
    @Test
    public void findByIdTest(){
        prods = setupProducts();

        when(jpaRepositoryInterface.findById(1L)).thenReturn(Optional.ofNullable(prods.get(0)));
        when(jpaRepositoryInterface.findById(2L)).thenReturn(Optional.ofNullable(prods.get(1)));
        when(jpaRepositoryInterface.findById(3L)).thenReturn(Optional.ofNullable(prods.get(2)));

        Assertions.assertEquals(prods.get(0), jpaRepository.findById(1L));
        Assertions.assertEquals(prods.get(1), jpaRepository.findById(2L));
        Assertions.assertEquals(prods.get(2), jpaRepository.findById(3L));
        Assertions.assertNotEquals(prods.get(0), jpaRepository.findById(3L));
        Assertions.assertNotEquals(prods.get(0), jpaRepository.findById(5L));

    }
}
