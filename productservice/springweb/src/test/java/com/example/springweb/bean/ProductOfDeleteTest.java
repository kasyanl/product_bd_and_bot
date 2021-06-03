package com.example.springweb.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductOfDeleteTest {


    ProductOfDelete productOfDelete;
    @BeforeEach
    void setUp(){
        productOfDelete = new ProductOfDelete();
    }

    @Test
    void testToString() {
        Date data = new Date();
        productOfDelete.setId(1);
        productOfDelete.setCategory("FRUITS");
        productOfDelete.setName("Apple");
        productOfDelete.setPrice(10.0);
        productOfDelete.setDiscount(50.0);
        productOfDelete.setActualPrice(5.0);
        productOfDelete.setTotalVolume(20.0);
        productOfDelete.setData(data);
        String expected = "ProductOfDelete(id=1, category=FRUITS, name=Apple, price=10.0, actualPrice=5.0," +
                " totalVolume=20.0, discount=50.0, data="+data+")";
        assertEquals(expected, productOfDelete.toString());

    }

    @Test
    void testEquals() {
        Date data = new Date();
        ProductOfDelete expected = new ProductOfDelete(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);
        ProductOfDelete actual = new ProductOfDelete(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);
        assertTrue(expected.equals(actual) && actual.equals(expected));
    }

    @Test
    void testHashCode() {
        Date data = new Date();
        ProductOfDelete expected = new ProductOfDelete(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);
        ProductOfDelete actual = new ProductOfDelete(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);
        assertEquals(expected.hashCode(), actual.hashCode());
    }
}