package com.example.homework28.Controller;

import com.example.homework28.Model.MyUser;
import com.example.homework28.Model.Order;
import com.example.homework28.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get/{id}")
    public ResponseEntity getOrder(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer id){
        return ResponseEntity.status(200).body(orderService.getOrder(myUser.getId(), id));
    }

    @GetMapping("/get-user")
    public ResponseEntity getOrdersByUser(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(orderService.getOrdersByUser(myUser));
    }

    @GetMapping("/get-product/{productId}")
    public ResponseEntity getOrdersByProduct(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer productId){
        return ResponseEntity.status(200).body(orderService.getOrdersByProduct(myUser, productId));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer productId, @RequestBody Order order){
        orderService.addOrder(myUser.getId(), productId,order);
        return ResponseEntity.status(200).body("Order added");
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal MyUser myUser, @RequestBody Order order, @PathVariable Integer orderId){
        orderService.updateOrder(myUser.getId(), order, orderId);
        return ResponseEntity.status(200).body("Order updated");
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity deleteOrder(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId){
        orderService.deleteOrder(myUser.getId(), orderId);
        return ResponseEntity.status(200).body("Order deleted");
    }

    @PutMapping("/assign/{orderId}/{productId}")
    public ResponseEntity assignProductToOrder(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId, @PathVariable Integer productId){
        orderService.assignProductToOrder(myUser.getId(), orderId, productId);
        return ResponseEntity.status(200).body("Order updated");
    }

    @PutMapping("/status/{orderId}/{status}")
    public ResponseEntity changeStatus(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer orderId, @PathVariable String status){
        orderService.changeStatus(myUser.getId(), orderId, status);
        return ResponseEntity.status(200).body("Status updated");
    }
}
