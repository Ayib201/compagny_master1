package com.groupeisi.com.company.mappers;

import com.groupeisi.com.company.dto.SaleDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Sales;
import com.groupeisi.com.company.entities.UserEntity;

import java.util.List;

public class SaleMapper {
    private SaleMapper() {
    }

    public static List<SaleDto> listSaleToListSaleDto(List<Sales> sales) {

        return sales.stream()
            .map(SaleMapper::toSaleDto)
            .toList();
    }

    public static SaleDto toSaleDto(Sales sale) {

        return SaleDto
                .builder()
                .id(sale.getId())
                .dateP(sale.getDateP())
                .quantity(sale.getQuantity())
                .productRef(sale.getProduct().getName())
                .userEmail(sale.getUser().getEmail())
                .build();
    }

    public static Sales toSale(SaleDto saleDto, Product product, UserEntity user) {
        return Sales.builder()
                .id(saleDto.getId())
                .dateP(saleDto.getDateP())
                .quantity(saleDto.getQuantity())
                .product(product)
                .user(user)
                .build();
    }

}
