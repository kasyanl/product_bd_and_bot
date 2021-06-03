package com.example.springweb.service;

import com.example.springweb.bean.Product;
import com.example.springweb.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private UtilService utilService;

    ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
        productService.setProductRepository(productRepository);
        productService.setUtilService(utilService);
    }

    @Test
    void findAll() {
        var data = new Date();
        Mockito.when(productRepository.findAll())
                .thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Fruits", "Apple", 20.0, 20.0, 16.0, 10.0, data)));

        List<Product> expected = List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Fruits", "Apple", 20.0, 20.0, 16.0, 10.0, data));

        List<Product> actual = productService.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        var data = new Date();
        Mockito.when(productRepository.findById(1))
                .thenReturn(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data));

        Product expected = new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data);

        Product actual = productService.findById(1);

        assertEquals(expected, actual);
    }

    @Test
    void fineCategoryForRead() {
        var data = new Date();
        Mockito.when(productRepository.findByCategory(Mockito.any(String.class)))
                .thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Fruits", "Apple", 20.0, 20.0, 16.0, 10.0, data)));

        List<Product> expected = List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Fruits", "Apple", 20.0, 20.0, 16.0, 10.0, data));

        List<Product> actual = productService.fineCategoryForRead("Fruits");

        assertEquals(expected, actual);
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepository).deleteById((isA(Integer.class)));
        productService.deleteProduct(0);
        verify(productRepository).deleteById(0);
    }

    @Test
    void saveProduct() {
        Mockito.when(utilService.createId()).thenReturn(1);
        Mockito.when(utilService.calculating(10.0, 20.0)).thenReturn(8.0);
        final Product product = new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0);
        productService.saveProduct("Fruits", "Banana", 10.0, 20.0, 10);
        verify(productRepository).save(product);
    }

    @Test
    void update() {
        Mockito.when(utilService.calculating(10.0, 50.0)).thenReturn(5.0);
        doNothing().when(productRepository).update(isA(String.class), isA(String.class),
                isA(Double.class), isA(Double.class), isA(Double.class), isA(Double.class), isA(Integer.class));
        productService.update(0, "Fruits", "Apple", 10.0, 50.0, 30.0);
        verify(productRepository).update("Fruits", "Apple", 10.0, 50.0, 5.0, 30.0, 0);
    }

    @Test
    void updateDiscountForCategory() {
        var data = new Date();
        Mockito.when(productRepository.findByCategory("Fruits"))
                .thenReturn(List.of(new Product(0, "Fruits", "Apple", 10.0, 50.0, 5.0, 30.0, data)));
        Mockito.when(utilService.calculating(10.0, 50.0)).thenReturn(5.0);
        productService.updateDiscountForCategory("Fruits", 50.0);
        verify(productRepository).update("Fruits", "Apple", 10.0, 50.0, 5.0, 30.0, 0);
    }

    @Test
    void sortByIdTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortById()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbyid");
        assertEquals(expected, actual);
    }

    @Test
    void sortByIdReverseTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByIdReverse()).
                thenReturn(List.of(
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data),
                        new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data)
                        ));
        List<Product> expected = List.of(
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data),
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data));
        List<Product> actual = productService.sort("sortbyidreverse");
        assertEquals(expected, actual);
    }
    @Test
    void sortByCategoryTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByCategory()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbycategory");
        assertEquals(expected, actual);
    }
    @Test
    void sortByCategoryReverseTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByCategoryReverse()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbycategoryreverse");
        assertEquals(expected, actual);
    }
    @Test
    void sortByNameTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByName()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbyname");
        assertEquals(expected, actual);
    }
    @Test
    void sortByNameReverseTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByNameReverse()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbynamereverse");
        assertEquals(expected, actual);
    }
    @Test
    void sortByPriceTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByPrice()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbyprice");
        assertEquals(expected, actual);
    }
    @Test
    void sortByPriceReverseTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByPriceReverse()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbypricereverse");
        assertEquals(expected, actual);
    }
    @Test
    void sortByDiscountTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByDiscount()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbydiscount");
        assertEquals(expected, actual);
    }
    @Test
    void sortByDiscountReverseTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByDiscountReverse()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbydiscountreverse");
        assertEquals(expected, actual);
    }
    @Test
    void sortByActualPriceTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByActualPrice()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbyactualprice");
        assertEquals(expected, actual);
    }
    @Test
    void sortByActualPriceReverseTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByActualPriceReverse()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbyactualpricereverse");
        assertEquals(expected, actual);
    }
    @Test
    void sortByTotalVolumeTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByTotalVolume()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbytotalvolume");
        assertEquals(expected, actual);
    }
    @Test
    void sortByTotalVolumeReverseTest() {
        Date data = new Date();
        Mockito.when(productRepository.sortByTotalVolumeReverse()).
                thenReturn(List.of(new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                        new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data)));
        List<Product> expected = List.of(
                new Product(1, "Fruits", "Banana", 10.0, 20.0, 8.0, 10.0, data),
                new Product(2, "Berries", "Apple", 20.0, 20.0, 16.0, 10.0, data));
        List<Product> actual = productService.sort("sortbytotalvolumereverce");
        assertEquals(expected, actual);
    }
}