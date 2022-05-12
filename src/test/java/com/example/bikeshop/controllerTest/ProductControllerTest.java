package com.example.bikeshop.controllerTest;

import com.example.bikeshop.businesslogic.ProductService;
import com.example.bikeshop.controller.ProductController;
import com.example.bikeshop.product.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the ProductController class.
 */
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    /**
     *
     */
    @InjectMocks
    ProductController productController;

    /**
     *
     */
    @MockBean
    ProductService productService;

    /**
     *
     */
    @Mock
    ProductService productServiceMock;
    /**
     *
     */
    @Autowired
    ObjectMapper objectMapper;

    /**
     *
     */
    @Autowired
    MockMvc mockMvc;

    /**
     *
     */
    @Test
    public void getProductsTrue(){
        Product prod1 = new Product(1, "CrankBrothers", "Pedals", 5, (float) 54.59);
        Product prod2 = new Product(2, "FatBar", "Handlebar", 2, (float) 104.69);
        Product prod3 = new Product(3, "ESI", "Grip", 6, (float) 12.76);
        List<Product> prods = new ArrayList<>();
        prods.add(prod1);
        prods.add(prod2);
        prods.add(prod3);

        when(productServiceMock.getProducts()).thenReturn(prods);
        Assertions.assertEquals(prods, productController.getProducts());
        Assertions.assertEquals(productController.getProducts().size(), 3);
    }

    /**
     *
     */
    @Test
    public void getProductsFalse(){
        Product prod1 = new Product(1, "CrankBrothers", "Pedals", 5, (float) 54.59);
        Product prod2 = new Product(2, "FatBar", "Handlebar", 2, (float) 104.69);
        Product prod3 = new Product(3, "ESI", "Grip", 6, (float) 12.76);
        List<Product> prods = new ArrayList<>();
        prods.add(prod1);
        prods.add(prod2);
        prods.add(prod3);

        when(productServiceMock.getProducts()).thenReturn(prods);
        Assertions.assertEquals(prods, productController.getProducts());
        Assertions.assertNotEquals(productController.getProducts().size(), 5);
    }

    /**
     *
     * @throws Exception An exception
     */
    @Test
    public void registerNewProductTest() throws Exception {
        Product prod1 = new Product("CrankBrothers", "Pedals", 5, (float) 54.59);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(prod1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Added successfully!")))
        ;
    }

    /**
     *
     * @throws Exception Another exception
     */
    @Test
    public void registerNewProductTestFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes("prod")))
                .andExpect(status().isBadRequest())
        ;

        Product prod1 = new Product("CrankBrothers", "Pedals", 5, (float) 54.59);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(prod1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Added successfully!")))
        ;
    }
}
