package com.example.homework28;

import com.example.homework28.Model.MyUser;
import com.example.homework28.Model.Order;
import com.example.homework28.Model.Product;
import com.example.homework28.Repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    MyUser user;

    Product product;

    Order order1, order2, order3;

    List<Order> orders;

    Set<Order> orderSet;

    @BeforeEach
    void setUp(){
        user = new MyUser(null, "user","1234","Admin",null);
        product = new Product(null,"product1",20.0,null);
        order1 = new Order(null,1,20.0,"06-06-2023","new",user,product);
        order2 = new Order(null,1,20.0,"06-06-2023","new",user,product);
        order3 = new Order(null,1,20.0,"06-06-2023","new",user,product);
        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orderSet = new HashSet<>(orders);
        product.setOrders(orderSet);
        user.setOrders(orderSet);
    }

    @Test
    public void findOrderById(){
        orderRepository.save(order1);
        order2 = orderRepository.findOrderById(order1.getId());
        Assertions.assertThat(order2).isEqualTo(order1);
    }

    @Test
    public void findOrdersByUser(){
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orders = orderRepository.findOrdersByUser(user);
        Assertions.assertThat(orders.get(0).getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    public void findOrdersByProduct(){
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orders = orderRepository.findOrdersByProduct(product);
        Assertions.assertThat(orders.get(0).getProduct().getId()).isEqualTo(product.getId());
    }
}
