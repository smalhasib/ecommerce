package com.hasib.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String houseNumber;
    private String road;
    private String area;
    private String postalCode;
    private String policeStation;
    private String district;
    private String division;

    public int getZonalCode() {
        return switch (division.toLowerCase()) {
            case "dhaka" -> 1;
            case "barisal" -> 2;
            case "chittagong" -> 3;
            case "khulna" -> 4;
            case "mymensingh" -> 5;
            case "rajshahi" -> 6;
            case "rangpur" -> 7;
            case "sylhet" -> 8;
            default -> 9;
        };
    }
}
