package com.example.homework28.Service;

import com.example.homework28.ApiException.ApiException;
import com.example.homework28.Model.MyUser;
import com.example.homework28.Model.Product;
import com.example.homework28.Repository.AuthRepository;
import com.example.homework28.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final AuthRepository authRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getProduct(Integer id){
        return productRepository.findProductById(id);
    }

    public void addProduct(MyUser user, Product product){
        //MyUser user = authRepository.findMyUserById(userId);
        if (user.getRole().equals("CUSTOMER"))
            throw new ApiException("Invalid");
        productRepository.save(product);
    }

    public void updateProduct(Integer userId, Product product, Integer productId){
        MyUser user = authRepository.findMyUserById(userId);
        Product p = productRepository.findProductById(productId);
        if (p == null || user.getRole().equals("CUSTOMER"))
            throw new ApiException("Invalid");
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        productRepository.save(p);
    }

    public void deleteProduct(Integer userId, Integer productId){
        MyUser user = authRepository.findMyUserById(userId);
        Product p = productRepository.findProductById(productId);
        if (p == null || !user.getRole().equals("ADMIN"))
            throw new ApiException("Invalid");
        productRepository.delete(p);
    }
}
