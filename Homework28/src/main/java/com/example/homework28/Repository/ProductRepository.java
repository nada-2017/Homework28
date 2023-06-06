package com.example.homework28.Repository;

import com.example.homework28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findProductById(Integer id);
}
