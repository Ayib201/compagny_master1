package com.groupeisi.com.company.mappers;

import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.entities.Product;

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
                .build();
    }

    public static Product toProduct(ProductDto productDto) {

        return Product.builder()
                .ref(productDto.getRef())
                .name(productDto.getName())
                .stock(productDto.getStock())
                .build();
    }
}
