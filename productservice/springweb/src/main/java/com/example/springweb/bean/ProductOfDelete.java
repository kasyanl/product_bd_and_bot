package com.example.springweb.bean;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productofdelete")
public class ProductOfDelete {
    @Id
    private Integer id;
    @Column(name = "category")
    private String category;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "actual_price")
    private double actualPrice;
    @Column(name = "total_volume")
    private double totalVolume;
    @Column(name = "discount")
    private double discount;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date data;

    public ProductOfDelete(Integer id, String category, String name, double price, double actualPrice, double totalVolume, double discount) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.actualPrice = actualPrice;
        this.totalVolume = totalVolume;
        this.discount = discount;
    }
}