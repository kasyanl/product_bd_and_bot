package com.example.springweb.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testToString() {
        Date data = new Date();
        product.setId(1);
        product.setCategory("FRUITS");
        product.setName("Apple");
        product.setPrice(10.0);
        product.setDiscount(50.0);
        product.setActualPrice(5.0);
        product.setTotalVolume(20.0);
        product.setData(data);
        String expected = "Product(id=1, category=FRUITS, name=Apple, price=10.0, discount=50.0, actualPrice=5.0," +
                " totalVolume=20.0, data=" + data + ")";
        assertEquals(expected, product.toString());

    }

    @Test
    void testEquals() {
        Date data = new Date();
        Product expected = new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);
        Product actual = new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);
        assertTrue(expected.equals(actual) && actual.equals(expected));
    }

    @Test
    void testHashCode() {
        Date data = new Date();
        Product expected = new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);
        Product actual = new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);
        assertEquals(expected.hashCode(), actual.hashCode());
    }
}
