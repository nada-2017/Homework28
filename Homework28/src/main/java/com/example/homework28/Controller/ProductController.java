package com.example.homework28.Controller;

import com.example.homework28.Model.MyUser;
import com.example.homework28.Model.Product;
import com.example.homework28.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(productService.getAll());
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getOrder(@PathVariable Integer id){
        return ResponseEntity.status(200).body(productService.getProduct(id));
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@AuthenticationPrincipal MyUser myUser, @RequestBody Product product){
        productService.addProduct(myUser, product);
        return ResponseEntity.status(200).body("Product added");
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity updateProduct(@AuthenticationPrincipal MyUser myUser, @RequestBody Product product,@PathVariable Integer productId){
        productService.updateProduct(myUser.getId(), product, productId);
        return ResponseEntity.status(200).body("Product updated");
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer productId){
        productService.deleteProduct(myUser.getId(), productId);
        return ResponseEntity.status(200).body("Product deleted");
    }
}
