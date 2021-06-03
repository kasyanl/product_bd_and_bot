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
@Table(name = "product")
public class Product {

    @Id
    private Integer id;
    @Column(name = "category")
    private String category;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "discount")
    private double discount;
    @Column(name = "actual_price")
    private double actualPrice;
    @Column(name = "total_volume")
    private double totalVolume;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date data;

    public Product(Integer id, String category, String name, double price, double discount, double actualPrice, double totalVolume) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.actualPrice = actualPrice;
        this.totalVolume = totalVolume;
    }
}