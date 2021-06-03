package com.example.springweb.service;

import com.example.springweb.bean.BuyProduct;
import com.example.springweb.bean.Product;
import com.example.springweb.repository.BuyProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class BuyProductServiceTest {

    @MockBean
    private ProductService productService;
    @MockBean
    private BuyProductRepository buyProductRepository;

    BuyProductService buyProductService;

    @BeforeEach
    void setUp() {
        buyProductService = new BuyProductService();
        buyProductService.setBuyProductRepository(buyProductRepository);
        buyProductService.setProductService(productService);
    }

    @Test
    void findAllBuyProduct() {
        Mockito.when(buyProductRepository.findAll())
                .thenReturn(List.of(new BuyProduct(1, "Banana", 10.0, 20.0, 200.0),
                        new BuyProduct(2, "Apple", 20.0, 20.0, 400.0)));

        List<BuyProduct> expected = List.of(new BuyProduct(1, "Banana", 10.0, 20.0, 200.0),
                new BuyProduct(2, "Apple", 20.0, 20.0, 400.0));

        List<BuyProduct> actual = buyProductService.findAllBuyProduct();

        assertEquals(expected, actual);
    }

    @Test
    void deleteBuy() {

        doNothing().when(buyProductRepository).deleteById(isA(Integer.class));
        buyProductService.deleteBuy(0);

        verify(buyProductRepository).deleteById(0);
    }

    @Test
    void cleanBuyDB() {
        doNothing().when(buyProductRepository).deleteAll();
        buyProductService.cleanBuyDB();

        verify(buyProductRepository).deleteAll();
    }

    @Test
    void saveBayProduct() {
        final BuyProduct buyProduct = new BuyProduct(1, "Banana", 8.0, 20.0, 160.0);
        var data = new Date();
        when(productService.findById(1))
                .thenReturn(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data));
        buyProductService.saveBayProduct(1, 20.0);
        verify(buyProductRepository).save(buyProduct);
    }

    @Test
    void saveBayProductIsPresent() {
        when(productService.findById(1))
                .thenReturn(new Product());
        buyProductService.saveBayProduct(1, 20.0);
        verify(productService).findById(1);
    }
}