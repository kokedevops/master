package com.example.store.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id //PK
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(min=2)
    private String name;

    @Min(1)
    private double price;

    @NotBlank
    @Column(columnDefinition="TEXT")
    private String description;


    @JsonManagedReference
    @OneToOne(mappedBy="product", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private ProductDetail detail;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="orders_has_products",
            joinColumns = @JoinColumn(name="product_id"), //Esta clase
            inverseJoinColumns = @JoinColumn(name="order_id") //La clase relacionada
    )
    private List<Order> orders;

}
