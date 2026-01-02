package com.groupeisi.com.company.services.sale;

import com.groupeisi.com.company.dto.ProductDto;

import java.util.List;


public interface ISaleService {
	List<ProductDto> getAll();
	boolean save(ProductDto produitDto);
}
