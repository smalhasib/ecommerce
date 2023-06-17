package com.hasib.organization.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Setter
@Getter
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private double quantity;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories = new ArrayList<>();

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private UserEntity seller;
}
