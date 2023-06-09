package com.hasib.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Setter
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long nid;
    private String address;
    private String email;
    private String username;
    private String password;
    private long accountNumber;
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();
    private boolean isVerified;
    private double money;
    @Builder.Default
    @JsonBackReference
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private Set<Transaction> sentTransactions = new HashSet<>();
    @Builder.Default
    @JsonBackReference
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Transaction> receivedTransactions = new HashSet<>();
}
