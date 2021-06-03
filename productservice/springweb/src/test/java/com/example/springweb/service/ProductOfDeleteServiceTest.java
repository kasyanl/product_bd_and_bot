package com.example.springweb.service;

import com.example.springweb.bean.Product;
import com.example.springweb.bean.ProductOfDelete;
import com.example.springweb.repository.ProductOfDeleteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductOfDeleteServiceTest {

    @MockBean
    private ProductOfDeleteRepository productOfDeleteRepository;
    @MockBean
    private ProductService productService;

    ProductOfDeleteService productOfDeleteService;

    @BeforeEach
    void setUp() {
        productOfDeleteService = new ProductOfDeleteService();
        productOfDeleteService.setProductOfDeleteRepository(productOfDeleteRepository);
        productOfDeleteService.setProductService(productService);
    }

    @Test
    void findAllDeleted() {
        var data = new Date();
        Mockito.when(productOfDeleteRepository.findAll())
                .thenReturn(List.of(new ProductOfDelete(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new ProductOfDelete(2, "Fruits", "Apple", 20.0, 20.0, 16.0, 10.0, data)));

        List<ProductOfDelete> expected = List.of(new ProductOfDelete(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new ProductOfDelete(2, "Fruits", "Apple", 20.0, 20.0, 16.0, 10.0, data));

        List<ProductOfDelete> actual = productOfDeleteService.findAllDeleted();

        assertEquals(expected, actual);
    }

    @Test
    void findProductOfBasketByID() {
        var data = new Date();
        Mockito.when(productOfDeleteRepository.findById(Mockito.any(Integer.class)))
                .thenReturn(Optional.of(new ProductOfDelete(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data)));

        Optional<ProductOfDelete> expected = Optional.of(new ProductOfDelete(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data));

        Optional<ProductOfDelete> actual = productOfDeleteService.findProductOfBasketByID(1);

        assertEquals(expected, actual);
    }

    @Test
    void deleteOfBasket() {
        doNothing().when(productOfDeleteRepository).deleteById((isA(Integer.class)));
        productOfDeleteService.deleteOfBasket(0);
        verify(productOfDeleteRepository).deleteById(0);
    }

    @Test
    void cleanBasket() {
        doNothing().when(productOfDeleteRepository).deleteAll();
        productOfDeleteService.cleanBasket();
        verify(productOfDeleteRepository).deleteAll();
    }

    @Test
    void saveProductOfDelete() {
        Mockito.when(productService.findById(1))
                .thenReturn(new Product(1, "Fruits", "Banana", 10.0, 50.0, 5.0, 50.0));
        final ProductOfDelete product = new ProductOfDelete(1, "Fruits", "Banana", 10.0, 5.0, 50.0, 50.0);
        productOfDeleteService.saveProductOfDelete(1);
        verify(productOfDeleteRepository).save(product);
    }

    @Test
    void saveProductOfDeleteIsPresent() {
        Mockito.when(productService.findById(1))
                .thenReturn(new Product());
        productOfDeleteService.saveProductOfDelete(1);
        verify(productService).findById(1);
    }
}