package com.groupeisi.com.company.services.sale;

import com.groupeisi.com.company.dao.product.IProductDao;
import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dao.sale.ISaleDao;
import com.groupeisi.com.company.dao.sale.SaleDao;
import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.dto.SaleDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Sales;
import com.groupeisi.com.company.mappers.ProductMapper;
import com.groupeisi.com.company.mappers.SaleMapper;

import java.util.List;

public class SaleService implements ISaleService {
	private final IProductDao productDao;
	private final ISaleDao saleDao = new SaleDao();

	public SaleService(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<SaleDto> getAll() {
		return SaleMapper.listSaleToListSaleDto(saleDao.list(Sales.class));
	}

	@Override
	public boolean save(SaleDto saleDto) {
		Product product = productDao.getProduct(saleDto.getProduct_ref());
		Sales sales = SaleMapper.toSale(saleDto, product);
		return saleDao.save(sales);
	}
}
