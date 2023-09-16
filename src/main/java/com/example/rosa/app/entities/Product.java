package com.example.rosa.app.entities;

import com.example.rosa.app.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private String destination;
    private Double profitabilityRate;
    private Integer minimalMonths;
    private Double administrationTax;
}
