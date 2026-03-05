package com.groupeisi.com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDto {

    private Long id;
    private Date dateP;
    private double quantity;
    private String productRef;
    private String userEmail;
}
