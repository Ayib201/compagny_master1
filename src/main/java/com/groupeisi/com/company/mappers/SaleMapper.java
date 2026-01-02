package com.groupeisi.com.company.mappers;

import com.groupeisi.com.company.dto.SaleDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Sale;
import com.groupeisi.com.company.entities.Sales;
import com.groupeisi.com.company.services.produit.ProductService;

import java.util.List;

public class SaleMapper {
    ProductService productService;
    private SaleMapper(ProductService productService) {
        this.productService = productService;
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
                .product_id(sale.getId())
                .build();
    }

    public static Sales toSale(SaleDto saleDto) {

        return Sales.builder()
                .product(saleDto.getProduct_id())
                .build();
    }
}
