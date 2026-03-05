package com.groupeisi.com.company.mappers;

import com.groupeisi.com.company.dto.PurchaseDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Purchases;
import com.groupeisi.com.company.entities.UserEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                .id(purchase.getId())
                .dateP(String.valueOf(purchase.getDateP()))
                .quantity(purchase.getQuantity())
                .productRef(purchase.getProduct().getName())
                .userEmail(purchase.getUser().getEmail())
                .build();
    }

    public static Purchases toPurchase(PurchaseDto purchaseDto, Product product, UserEntity user) throws ParseException {
        return Purchases.builder()
                .id(purchaseDto.getId())
                .dateP(new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDto.getDateP()))
                .quantity(purchaseDto.getQuantity())
                .product(product)
                .user(user)
                .build();
    }
}
