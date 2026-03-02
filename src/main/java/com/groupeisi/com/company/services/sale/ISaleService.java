package com.groupeisi.com.company.services.sale;
import com.groupeisi.com.company.dto.SaleDto;

import java.util.List;
import java.util.Optional;


public interface ISaleService {
	List<SaleDto> getAll();
	boolean save(SaleDto saleDto);
	boolean delete(Long id);
	boolean update(SaleDto saleDto);
	Optional<SaleDto> get(Long id);
}
