package kasyan.springweb.repository;

import kasyan.springweb.bean.BuyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyProductRepository extends JpaRepository<BuyProduct, Integer> {

}