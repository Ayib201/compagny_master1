package com.groupeisi.com.company.services.produit;

import com.groupeisi.com.company.dto.ProductDto;

import java.util.List;
import java.util.Optional;


public interface IProductService {
	List<ProductDto> getAll();
	boolean save(ProductDto produitDto);
	boolean delete(String id);
	boolean update(ProductDto productDto);
	Optional<ProductDto> get(String id);
}
