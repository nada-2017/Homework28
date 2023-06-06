package com.example.homework28.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    private Double price;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Order> orders;
}
