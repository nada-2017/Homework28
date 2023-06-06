package com.example.homework28.Repository;

import com.example.homework28.Model.MyUser;
import com.example.homework28.Model.Order;
import com.example.homework28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findOrderById(Integer id);

    List<Order> findOrdersByUser(MyUser user);

    List<Order> findOrdersByProduct(Product product);
}
