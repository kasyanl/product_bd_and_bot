package by.kasyan.database.service;

import by.kasyan.database.bean.Product;
import by.kasyan.database.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;
    private UtilService utilService;

    //отправка запроса на получение всех продуктов из основной БД
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    //находим конкретный Product по ID
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    public Optional <Product> findByName(String name){
        return productRepository.findByName(name);
    }

    public List<String> findByCategoryName(){
        List<Product> list = productRepository.findAll();
        List<String> str = new ArrayList<>();
        for(Product product : list){
            str.add(product.getCategory());
        }
        Set<String> set = new HashSet<>(str);
        str.clear();
        str.addAll(set);
        return str;
    }

    // ищем все Products одной категории и отправляем в БД соответствующий запрос
    public List<Product> fineCategoryForRead(String category) {
        return productRepository.findByCategory(category);
    }

    //находим Product по его ID и отправка запроса в БД для удаления
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    /* отправка запроса на добавление новой записи в БД Product
 и автоматическим расчетом цены с учетом скидки */
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
    public void update(int id, String category, String name, double price, double discount, double totalVolume) {
        double actualPrice = utilService.calculating(price, discount);
        productRepository.update(category, name, price, discount, actualPrice, totalVolume, id);
    }

    // установка скидки для одной категории
    public void updateDiscountForCategory(String category, double discount) {

        List<Product> listCategory = fineCategoryForRead(category);
        for (Product product : listCategory) {
            update(product.getId(), category, product.getName(), product.getPrice(), discount, product.getTotalVolume());
        }
    }

    public List<Product> sort(String sort) {
        return switch (sort) {
            case "sortbyid", "sortbyidguest" -> productRepository.sortById();
            case "sortbyidreverse", "sortbyidreverseguest" -> productRepository.sortByIdReverse();
            case "sortbycategory", "sortbycategoryguest" -> productRepository.sortByCategory();
            case "sortbycategoryreverse", "sortbycategoryreverseguest" -> productRepository.sortByCategoryReverse();
            case "sortbyname", "sortbynameguest" -> productRepository.sortByName();
            case "sortbynamereverse", "sortbynamereverseguest" -> productRepository.sortByNameReverse();
            case "sortbyprice", "sortbypriceguest" -> productRepository.sortByPrice();
            case "sortbypricereverse", "sortbypricereverseguest" -> productRepository.sortByPriceReverse();
            case "sortbydiscount", "sortbydiscountguest" -> productRepository.sortByDiscount();
            case "sortbydiscountreverse", "sortbydiscountreverseguest" -> productRepository.sortByDiscountReverse();
            case "sortbyactualprice", "sortbyactualpriceguest" -> productRepository.sortByActualPrice();
            case "sortbyactualpricereverse", "sortbyactualpricereverseguest" -> productRepository.sortByActualPriceReverse();
            case "sortbytotalvolume", "sortbytotalvolumeguest" -> productRepository.sortByTotalVolume();
            default -> productRepository.sortByTotalVolumeReverse();
        };
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