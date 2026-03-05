package com.groupeisi.com.company.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
public class Product implements Serializable {
    @Id
    private String ref;
    @Column(nullable = false,unique = true)
    private String name;
    private double stock;
    @ManyToOne
    @JoinColumn(name = "user_email")
    private UserEntity user;
}
