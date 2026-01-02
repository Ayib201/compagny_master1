package com.groupeisi.com.company.services.produit;

import com.groupeisi.com.company.dto.ProductDto;

import java.util.List;


public interface IProductService {
	List<ProductDto> getAll();
	boolean save(ProductDto produitDto);
}
