package by.kasyan.database.repository;

import by.kasyan.database.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(int id);
    List<Product> findByCategory(String category);
    Optional <Product> findByName(String name);

    @Modifying
    @Query("update Product p set p.category=:category, p.name=:name, p.price=:price," +
            " p.discount=:discount, p.actualPrice=:actualPrice, p.totalVolume=:totalVolume where p.id=:id")
    void update(@Param(value = "category") String category, @Param(value = "name") String name,
                @Param(value = "price") double price, @Param(value = "discount") double discount,
                @Param(value = "actualPrice") double actualPrice, @Param(value = "totalVolume") double totalVolume,
                @Param(value = "id") int id);

    @Query("FROM Product P ORDER BY P.id")
    List<Product> sortById();

    @Query("FROM Product P ORDER BY P.id DESC")
    List<Product> sortByIdReverse();

    @Query("FROM Product P ORDER BY P.category")
    List<Product> sortByCategory();

    @Query("FROM Product P ORDER BY P.category DESC")
    List<Product> sortByCategoryReverse();

    @Query("FROM Product P ORDER BY P.name")
    List<Product> sortByName();

    @Query("FROM Product P ORDER BY P.name DESC")
    List<Product> sortByNameReverse();

    @Query("FROM Product P ORDER BY P.price")
    List<Product> sortByPrice();

    @Query("FROM Product P ORDER BY P.price DESC")
    List<Product> sortByPriceReverse();

    @Query("FROM Product P ORDER BY P.discount")
    List<Product> sortByDiscount();

    @Query("FROM Product P ORDER BY P.discount DESC")
    List<Product> sortByDiscountReverse();

    @Query("FROM Product P ORDER BY P.actualPrice")
    List<Product> sortByActualPrice();

    @Query("FROM Product P ORDER BY P.actualPrice DESC")
    List<Product> sortByActualPriceReverse();

    @Query("FROM Product P ORDER BY P.totalVolume")
    List<Product> sortByTotalVolume();

    @Query("FROM Product P ORDER BY P.totalVolume DESC")
    List<Product> sortByTotalVolumeReverse();
}