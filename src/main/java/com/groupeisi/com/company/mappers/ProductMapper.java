package com.groupeisi.com.company.mappers;

import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.UserEntity;

import java.util.List;

public class ProductMapper {

    private ProductMapper() {

    }

    public static List<ProductDto> listProductToListProductDto(List<Product> products) {

        return products.stream()
            .map(ProductMapper::toProductDto)
            .toList();
    }

    public static ProductDto toProductDto(Product product) {

        return ProductDto
                .builder()
                .ref(product.getRef())
                .name(product.getName())
                .stock(product.getStock())
                .userEmail(product.getUser().getEmail())
                .build();
    }

    public static Product toProduct(ProductDto productDto, UserEntity user) {

        return Product.builder()
                .ref(productDto.getRef())
                .name(productDto.getName())
                .stock(productDto.getStock())
                .user(user)
                .build();
    }
}
