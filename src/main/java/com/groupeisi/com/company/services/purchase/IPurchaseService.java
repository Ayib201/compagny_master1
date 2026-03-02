package com.groupeisi.com.company.services.purchase;

import com.groupeisi.com.company.dto.PurchaseDto;

import java.util.List;
import java.util.Optional;


public interface IPurchaseService {
	List<PurchaseDto> getAll();
	boolean save(PurchaseDto purchaseDto);
	boolean delete(Long id);
	boolean update(PurchaseDto purchaseDto);
	Optional<PurchaseDto> get(Long id);
}
