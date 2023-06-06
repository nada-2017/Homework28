package com.example.homework28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "MyOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    private Double totalPrice;

    @NotEmpty(message = "date received is required")
    private String dateReceived;

    @Pattern(regexp = "\\b(?:new|inProgress|completed)\\b",message = "status Not Valid")
    @Column(columnDefinition = "varchar(10) check(status='new' or status='inProgress' or status='completed')")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private MyUser user;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
