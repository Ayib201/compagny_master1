package com.groupeisi.com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {
    private Long id;
    private String dateP;
    private double quantity;
    private String productRef;
    private String userEmail;
}
