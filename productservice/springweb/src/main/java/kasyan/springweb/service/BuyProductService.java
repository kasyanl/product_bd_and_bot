package kasyan.springweb.service;

import kasyan.springweb.bean.BuyProduct;
import kasyan.springweb.repository.BuyProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BuyProductService {

    private BuyProductRepository buyProductRepository;
    private ProductService productService;

    //отправка запроса на получение всех ранее удаленных продуктов из основной БД
    public List<BuyProduct> findAllBuyProduct() {
        return buyProductRepository.findAll();
    }

    //находим Product по его ID с писке покупок и отправляем запрос на его даление
    public void deleteBuy(int id) {
        buyProductRepository.deleteById(id);
    }

    // очистка БД с покупками
    public void cleanBuyDB() {
        buyProductRepository.deleteAll();
    }

    // выбор продукта для покупки (передаем количество или вес продукта), добавляем в отдельную БД
    public void saveBayProduct(int id, double quantity) {
        var product = productService.findById(id);
        var buyProduct = new BuyProduct();

            double totalPrice = product.getActualPrice() * quantity;

            buyProduct.setId(product.getId());
            buyProduct.setName(product.getName());
            buyProduct.setActualPrice(product.getActualPrice());
            buyProduct.setQuantity(quantity);
            buyProduct.setTotalPrice(totalPrice);

            buyProductRepository.save(buyProduct);
        }

    @Autowired
    public void setBuyProductRepository(BuyProductRepository buyProductRepository) {
        this.buyProductRepository = buyProductRepository;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}