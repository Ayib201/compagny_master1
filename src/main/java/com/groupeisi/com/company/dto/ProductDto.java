package com.groupeisi.com.company.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ProductDto {
    private String ref;
    private String name;
    private double stock;
}
