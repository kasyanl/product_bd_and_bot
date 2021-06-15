package by.kasyan.database.bean;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}