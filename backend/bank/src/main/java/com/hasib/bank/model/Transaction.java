//package com.hasib.bank.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@Builder
//@Entity
//@Table(name = "transactions")
//@NoArgsConstructor
//@AllArgsConstructor
//public class Transaction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "sender_id")
//    private UserEntity sender;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "receiver_id")
//    private UserEntity receiver;
//    private long amount;
//    private LocalDateTime createdAt;
//}
