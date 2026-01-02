package com.groupeisi.com.company.mappers;

import com.groupeisi.com.company.dto.PurchaseDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Purchases;

import java.util.List;

public class PurchaseMapper {
    private PurchaseMapper() {
    }

    public static List<PurchaseDto> listPurchaseToListPurchaseDto(List<Purchases> purchases) {

        return purchases.stream()
                .map(PurchaseMapper::toPurchaseDto)
                .toList();
    }

    public static PurchaseDto toPurchaseDto(Purchases purchase) {

        return PurchaseDto
                .builder()
                .dateP(purchase.getDateP())
                .quantity(purchase.getQuantity())
                .product_ref(purchase.getProduct().getName())
                .build();
    }

    public static Purchases toPurchase(PurchaseDto purchaseDto, Product product) {
        return Purchases.builder()
                .dateP(purchaseDto.getDateP())
                .quantity(purchaseDto.getQuantity())
                .product(product)
                .build();
    }
}
