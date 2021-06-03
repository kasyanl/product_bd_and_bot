package com.example.springweb.repository;

import com.example.springweb.bean.BuyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyProductRepository extends JpaRepository<BuyProduct, Integer> {

}