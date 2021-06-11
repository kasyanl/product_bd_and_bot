package com.example.springweb.service;

import com.example.springweb.bean.ProductOfDelete;
import com.example.springweb.repository.ProductOfDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductOfDeleteService {

    private ProductOfDeleteRepository productOfDeleteRepository;
    private ProductService productService;

    //отправка запроса на получение всех ранее удаленных продуктов из основной БД
    public List<ProductOfDelete> findAllDeleted() {
        return productOfDeleteRepository.findAll();
    }

    public Optional<ProductOfDelete> findProductOfBasketByID(int id) {
        return productOfDeleteRepository.findById(id);
    }

    //находим Product по его ID  в корзине и отправка запроса для удаления
    public void deleteOfBasket(int id) {
        productOfDeleteRepository.deleteById(id);
    }

    //очистка всех данных из корзины
    public void cleanBasket() {
        productOfDeleteRepository.deleteAll();
    }

    public void saveProductOfDelete(int id) {

        var productOfDelete = new ProductOfDelete();
        var product = productService.findById(id);

            productOfDelete.setId(product.getId());
            productOfDelete.setCategory(product.getCategory());
            productOfDelete.setName(product.getName());
            productOfDelete.setPrice(product.getPrice());
            productOfDelete.setDiscount(product.getDiscount());
            productOfDelete.setActualPrice(product.getActualPrice());
            productOfDelete.setTotalVolume(product.getTotalVolume());
            productOfDeleteRepository.save(productOfDelete);
        }

    @Autowired
    public void setProductOfDeleteRepository(ProductOfDeleteRepository productOfDeleteRepository) {
        this.productOfDeleteRepository = productOfDeleteRepository;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}