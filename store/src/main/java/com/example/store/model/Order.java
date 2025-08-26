package com.example.store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String clientId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="orders_has_products",
            joinColumns = @JoinColumn(name="order_id"), //Esta clase
            inverseJoinColumns = @JoinColumn(name="product_id") //La clase relacionada
    )
    private List<Product> products;
    
}
