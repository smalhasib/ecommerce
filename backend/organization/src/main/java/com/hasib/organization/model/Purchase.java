package com.hasib.organization.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private UserEntity buyer;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<PurchaseItem> purchaseItems = new HashSet<>();

    private double totalAmount;
    private int transactionId;
    private LocalDateTime purchasedAt;
}
