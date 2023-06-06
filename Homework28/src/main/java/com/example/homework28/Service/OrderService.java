package com.example.homework28.Service;

import com.example.homework28.ApiException.ApiException;
import com.example.homework28.Model.MyUser;
import com.example.homework28.Model.Order;
import com.example.homework28.Model.Product;
import com.example.homework28.Repository.AuthRepository;
import com.example.homework28.Repository.OrderRepository;
import com.example.homework28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AuthRepository authRepository;

    public List<Order> getOrdersByUser(MyUser user){
        return orderRepository.findOrdersByUser(user);
    }

    public List<Order> getOrdersByProduct(MyUser user, Integer productId){
        if (!user.getRole().equals("ADMIN"))
            throw new ApiException("Invalid");
        Product product = productRepository.findProductById(productId);
        return orderRepository.findOrdersByProduct(product);
    }

    public void addOrder(Integer userId, Integer productId, Order order){
        MyUser user = authRepository.findMyUserById(userId);
        Product product = productRepository.findProductById(productId);
        if (user == null || product == null)
            throw new ApiException("Invalid");
        order.setTotalPrice(product.getPrice()*order.getQuantity());
        order.setStatus("new");
        order.setProduct(product);
        order.setUser(user);
        orderRepository.save(order);
    }

    public void updateOrder(Integer userId, Order order, Integer orderId){
        MyUser user = authRepository.findMyUserById(userId);
        Order oldOrder = orderRepository.findOrderById(orderId);
        if(user == null ||(userId != oldOrder.getUser().getId() && user.getRole().equals("CUSTOMER")))
            throw new ApiException("Invalid");
        oldOrder.setQuantity(order.getQuantity());
        oldOrder.setDateReceived(order.getDateReceived());
        oldOrder.setTotalPrice(oldOrder.getProduct().getPrice()*oldOrder.getQuantity());
        orderRepository.save(oldOrder);
    }

    public void deleteOrder(Integer userId, Integer orderId){
        MyUser user = authRepository.findMyUserById(userId);
        Order order = orderRepository.findOrderById(orderId);
        if(user == null || order == null || (userId != order.getUser().getId() && user.getRole().equals("CUSTOMER")))
            throw new ApiException("Invalid");
        if (order.getStatus().equalsIgnoreCase("inProgress") || order.getStatus().equalsIgnoreCase("completed"))
            throw new ApiException("Can't be deleted");
        orderRepository.delete(order);
    }

    public void assignProductToOrder(Integer userId, Integer orderId, Integer productId){
        MyUser user = authRepository.findMyUserById(userId);
        Order order = orderRepository.findOrderById(orderId);
        Product product = productRepository.findProductById(productId);
        if(user == null ||(userId != order.getUser().getId() && user.getRole().equalsIgnoreCase("customer")))
            throw new ApiException("Invalid");
        order.setProduct(product);
        order.setTotalPrice(product.getPrice()*order.getQuantity());
        orderRepository.save(order);
    }

    public void changeStatus(Integer userId, Integer orderId, String status){
        MyUser user = authRepository.findMyUserById(userId);
        Order order = orderRepository.findOrderById(orderId);
        if (user == null || order == null || !user.getRole().equalsIgnoreCase("admin"))
            throw new ApiException("Invalid");
        if (order.getStatus().equalsIgnoreCase("inProgress") || order.getStatus().equalsIgnoreCase("completed") || order.getStatus().equalsIgnoreCase("new"))
            order.setStatus(status);
        else
            throw new ApiException("Invalid");
        orderRepository.save(order);
    }

    public Order getOrder(Integer userId, Integer orderId){
        MyUser user = authRepository.findMyUserById(userId);
        Order order = orderRepository.findOrderById(orderId);
        if(user == null || order == null || (userId != order.getUser().getId() && user.getRole().equalsIgnoreCase("customer")))
            throw new ApiException("Invalid");
        return order;
    }
}
