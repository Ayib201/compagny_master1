package com.groupeisi.com.company.services.purchase;

import com.groupeisi.com.company.dto.PurchaseDto;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


public interface IPurchaseService {
	List<PurchaseDto> getAll();
	boolean save(PurchaseDto purchaseDto) throws ParseException;
	boolean delete(Long id);
	boolean update(PurchaseDto purchaseDto) throws ParseException;
	Optional<PurchaseDto> get(Long id);
}
