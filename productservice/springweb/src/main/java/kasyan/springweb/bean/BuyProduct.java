package kasyan.springweb.bean;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "buyProduct")
public class BuyProduct {
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "actual_price")
    private double actualPrice;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "total_price")
    private double totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BuyProduct that = (BuyProduct) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1548262949;
    }
}