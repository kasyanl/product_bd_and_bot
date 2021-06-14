package kasyan.springweb.service;

import kasyan.springweb.bean.BuyProduct;
import kasyan.springweb.bean.Product;
import kasyan.springweb.bean.ProductOfDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtilService {
    private BuyProductService buyProductService;
    private ProductOfDeleteService productOfDeleteService;
    private ProductService productService;

    // расчет общей суммы покупок
    public double totalPrise() {
        List<BuyProduct> products = buyProductService.findAllBuyProduct();
        double count = 0;
        if (!products.isEmpty()) {
            for (BuyProduct product : products) {
                count += product.getTotalPrice();
            }
        }
        return count;
    }

    // проверка, чтобы не ввести больше количество,
    public boolean checkingForNumber(double quantity, double totalVolume) {
        return quantity <= totalVolume;
    }

    // проверка, пуста ли корзина
    @Transactional
    public boolean basketIsEmpty() {
        List<ProductOfDelete> newList = productOfDeleteService.findAllDeleted();
        return newList.isEmpty();
    }

    // метод для расчета стоимости с учетом скидки
    public double calculating(double price, double discount) {
        return (price - (price * discount / 100));
    }

    // формирование ID с одновременной проверкой (если есть пропуск (например 2, 3, ,5 то будет присвоен id=4))
    public int createId() {
        List<Product> newList = productService.findAll();
        var id = 0; // по умолчанию id = 0
        if (!newList.isEmpty()) { // если записи имеются, проверяем на пропущенные id
            int i = id;
            for (Product product : newList) { // проверяем
                if (product.getId() == i) i++; // теперь i больше текущего id на 1
                id = i; // присваиваем значение id (здесь id на 1 больше последнего проверенного)
            }
        }
        return id;
    }

    // сохранение данных после изменения
    @Transactional
    public void endTransaction() {
        for (BuyProduct buyProduct : buyProductService.findAllBuyProduct()) {
            var product = productService.findById(buyProduct.getId());

            double totalVolume = product.getTotalVolume() - buyProduct.getQuantity();
            productService.update(product.getId(), product.getCategory(), product.getName(),
                    product.getPrice(), product.getDiscount(), totalVolume);
        }
    }

    // восстановление всех записей из корзины
    @Transactional
    public void recoveryAllProduct() {
        List<ProductOfDelete> newList = productOfDeleteService.findAllDeleted();
        for (var productOfDelete : newList) {
            productService.saveProduct(productOfDelete.getCategory(), productOfDelete.getName(),
                    productOfDelete.getPrice(), productOfDelete.getDiscount(), productOfDelete.getTotalVolume());
        }
        productOfDeleteService.cleanBasket();
    }

    //восстанавливаем удаленный ранее Product по его ID и отправка запроса в БД
    @Transactional
    public void recovered(int id) {
        var productOfDeleted = productOfDeleteService.findProductOfBasketByID(id);
        if (productOfDeleted.isPresent()) {
            var productOfDelete = productOfDeleted.get();
            productService.saveProduct(productOfDelete.getCategory(), productOfDelete.getName(),
                    productOfDelete.getPrice(), productOfDelete.getDiscount(), productOfDelete.getTotalVolume());
            productOfDeleteService.deleteOfBasket(id);
        }
    }

    @Autowired
    public void setBuyProductService(BuyProductService buyProductService) {
        this.buyProductService = buyProductService;
    }

    @Autowired
    public void setProductOfDeleteService(ProductOfDeleteService productOfDeleteService) {
        this.productOfDeleteService = productOfDeleteService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}