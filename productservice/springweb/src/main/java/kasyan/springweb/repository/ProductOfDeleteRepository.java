package kasyan.springweb.repository;

import kasyan.springweb.bean.ProductOfDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOfDeleteRepository extends JpaRepository<ProductOfDelete, Integer> {
}