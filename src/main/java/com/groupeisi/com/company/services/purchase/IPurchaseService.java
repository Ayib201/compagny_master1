package com.groupeisi.com.company.services.purchase;

import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.dto.PurchaseDto;

import java.util.List;


public interface IPurchaseService {
	List<PurchaseDto> getAll();
	boolean save(PurchaseDto purchaseDto);
}
