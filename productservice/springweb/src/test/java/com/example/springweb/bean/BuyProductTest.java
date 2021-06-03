package com.example.springweb.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyProductTest {

    BuyProduct buyProduct;

    @BeforeEach
    public void setUp() {
        buyProduct = new BuyProduct();
    }

    @Test
    void testEquals() {
        BuyProduct expected = new BuyProduct(1, "Banana", 8.0, 20.0, 160.0);
        BuyProduct actual = new BuyProduct(1, "Banana", 8.0, 20.0, 160.0);
        assertTrue(expected.equals(actual) && actual.equals(expected));
    }

    @Test
    void testHashCode() {
        BuyProduct expected = new BuyProduct(1, "Banana", 8.0, 20.0, 160.0);
        BuyProduct actual = new BuyProduct(1, "Banana", 8.0, 20.0, 160.0);
        assertEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    void testToString() {
        buyProduct.setId(1);
        buyProduct.setName("Banana");
        buyProduct.setActualPrice(8.0);
        buyProduct.setQuantity(20.0);
        buyProduct.setTotalPrice(160.0);
        String expected = "BuyProduct(" +
                "id=1" +
                ", name=Banana" +
                ", actualPrice=8.0" +
                ", quantity=20.0" +
                ", totalPrice=160.0)";
        String actual = buyProduct.toString();
        assertEquals(expected, actual);
    }
}