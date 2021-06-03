package com.example.springweb.service;

import com.example.springweb.bean.Product;
import com.example.springweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private UtilService utilService;

    //отправка запроса на получение всех продуктов из основной БД
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    //находим конкретный Product по ID
    @Transactional
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Optional <Product> findByName(String name){
        return productRepository.findByName(name);
    }

    // ищем все Products одной категории и отправляем в БД соответствующий запрос
    @Transactional
    public List<Product> fineCategoryForRead(String category) {
        return productRepository.findByCategory(category);
    }

    //находим Product по его ID и отправка запроса в БД для удаления
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    /* отправка запроса на добавление новой записи в БД Product
 и автоматическим расчетом цены с учетом скидки */
    @Transactional
    public void saveProduct(String category, String name, double price, double discount, double totalVolume) {
        int id = utilService.createId();
        double actualPrice = utilService.calculating(price, discount);
        var product = new Product();
        product.setId(id);
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setActualPrice(actualPrice);
        product.setTotalVolume(totalVolume);

        productRepository.save(product);
    }

    // отправляем запрос в БД на обновление Product по ID
    @Transactional
    public void update(int id, String category, String name, double price, double discount, double totalVolume) {
        double actualPrice = utilService.calculating(price, discount);
        productRepository.update(category, name, price, discount, actualPrice, totalVolume, id);
    }

    // установка скидки для одной категории
    @Transactional
    public void updateDiscountForCategory(String category, double discount) {

        List<Product> listCategory = fineCategoryForRead(category);
        for (Product product : listCategory) {
            update(product.getId(), category, product.getName(), product.getPrice(), discount, product.getTotalVolume());
        }
    }

    public List<Product> sort(String sort) {
        switch (sort) {
            case "sortbyid":
            case "sortbyidguest":
                return productRepository.sortById();
            case "sortbyidreverse":
            case "sortbyidreverseguest":
                return productRepository.sortByIdReverse();
            case "sortbycategory":
            case "sortbycategoryguest":
                return productRepository.sortByCategory();
            case "sortbycategoryreverse":
            case "sortbycategoryreverseguest":
                return productRepository.sortByCategoryReverse();
            case "sortbyname":
            case "sortbynameguest":
                return productRepository.sortByName();
            case "sortbynamereverse":
            case "sortbynamereverseguest":
                return productRepository.sortByNameReverse();
            case "sortbyprice":
            case "sortbypriceguest":
                return productRepository.sortByPrice();
            case "sortbypricereverse":
            case "sortbypricereverseguest":
                return productRepository.sortByPriceReverse();
            case "sortbydiscount":
            case "sortbydiscountguest":
                return productRepository.sortByDiscount();
            case "sortbydiscountreverse":
            case "sortbydiscountreverseguest":
                return productRepository.sortByDiscountReverse();
            case "sortbyactualprice":
            case "sortbyactualpriceguest":
                return productRepository.sortByActualPrice();
            case "sortbyactualpricereverse":
            case "sortbyactualpricereverseguest":
                return productRepository.sortByActualPriceReverse();
            case "sortbytotalvolume":
            case "sortbytotalvolumeguest":
                return productRepository.sortByTotalVolume();
            default:
                return productRepository.sortByTotalVolumeReverse();
        }
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUtilService(UtilService utilService) {
        this.utilService = utilService;
    }
}