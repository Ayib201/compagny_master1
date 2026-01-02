package com.groupeisi.com.company.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Product {
    @Id
    private String ref;
    @Column(name = "name",length = 55, nullable = false,unique = true)
    private String name;
    private double stock;
}
