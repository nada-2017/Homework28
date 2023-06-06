package com.example.homework28.Controller;

import com.example.homework28.Model.MyUser;
import com.example.homework28.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(authService.getAll());
    }

    @GetMapping("/get-id")
    public ResponseEntity getUser(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(authService.getUser(myUser.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(200).body("User registered");
    }

    @PostMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body("Welcome admin");
    }

    @PostMapping("/customer")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("Welcome customer");
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("Login ");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("Logout ");
    }

}

