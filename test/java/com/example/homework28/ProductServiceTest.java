package com.example.homework28;

import com.example.homework28.Model.MyUser;
import com.example.homework28.Model.Order;
import com.example.homework28.Model.Product;
import com.example.homework28.Repository.AuthRepository;
import com.example.homework28.Repository.ProductRepository;
import com.example.homework28.Service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    AuthRepository authRepository;

    MyUser user;

    Product product1, product2;

    List<Product> products, productList;

    Order order1, order2, order3;

    List<Order> orders;
    Set<Order> orderSet;

    @BeforeEach
    void setUp(){
        user = new MyUser(null, "user","1234","Admin",null);
        product1 = new Product(null,"product1",20.0,null);
        product2 = new Product(null,"product2",20.0,null);
        order1 = new Order(null,1,20.0,"06-06-2023","new",user,product1);
        order2 = new Order(null,1,20.0,"06-06-2023","new",user,product1);
        order3 = new Order(null,1,20.0,"06-06-2023","new",user,product1);
        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orderSet = new HashSet<>(orders);
        product1.setOrders(orderSet);
        product2.setOrders(orderSet);
        user.setOrders(orderSet);
        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
    }

    @Test
    public void getAll(){
        when(productRepository.findAll()).thenReturn(products);
        productList = productService.getAll();
        Assertions.assertEquals(productList,products);
        Assertions.assertEquals(2,products.size());
        verify(productRepository,times(1)).findAll();
    }

    @Test
    public void getProduct(){
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        product2 = productService.getProduct(product1.getId());
        Assertions.assertEquals(product2,product1);
        verify(productRepository,times(1)).findProductById(product1.getId());
    }

    @Test
    public void addProduct(){
        productService.addProduct(user,product1);
        verify(productRepository,times(1)).save(product1);
    }

    @Test
    public void updateProduct(){
        when(authRepository.findMyUserById(user.getId())).thenReturn(user);
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.updateProduct(user.getId(),product1,product1.getId());
        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(productRepository,times(1)).findProductById(product1.getId());
    }

    @Test
    public void deleteProduct(){
        when(authRepository.findMyUserById(user.getId())).thenReturn(user);
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.deleteProduct(user.getId(),product1.getId());
        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(productRepository,times(1)).findProductById(product1.getId());
    }
}
