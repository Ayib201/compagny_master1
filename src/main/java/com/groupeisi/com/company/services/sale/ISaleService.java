package com.groupeisi.com.company.services.sale;

import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.dto.SaleDto;

import java.util.List;


public interface ISaleService {
	List<SaleDto> getAll();
	boolean save(SaleDto saleDto);
}
