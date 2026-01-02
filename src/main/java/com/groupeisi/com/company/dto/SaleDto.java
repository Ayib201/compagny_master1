package com.groupeisi.com.company.dto;

import com.groupeisi.com.company.entities.Product;
import jakarta.persistence.*;
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

    private long id;
    private Date dateP;
    private double quantity;
    private String product_ref;
}
