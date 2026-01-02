package com.groupeisi.com.company.mappers;

import com.groupeisi.com.company.dto.SaleDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Sales;

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
                .dateP(sale.getDateP())
                .quantity(sale.getQuantity())
                .product_ref(sale.getProduct().getName())
                .build();
    }

    public static Sales toSale(SaleDto saleDto, Product product) {
        return Sales.builder()
                .dateP(saleDto.getDateP())
                .quantity(saleDto.getQuantity())
                .product(product)
                .build();
    }

}
