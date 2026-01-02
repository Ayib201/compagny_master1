package com.groupeisi.com.company.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
public class Product {
    @Id
    private String ref;
    @Column(nullable = false,unique = true)
    private String name;
    private double stock;
}
