package com.groupeisi.com.company.services.sale;

import com.groupeisi.com.company.dao.product.IProductDao;
import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dao.sale.ISaleDao;
import com.groupeisi.com.company.dao.sale.SaleDao;
import com.groupeisi.com.company.dto.SaleDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Sales;
import com.groupeisi.com.company.mappers.SaleMapper;

import java.util.List;
import java.util.Optional;

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
		Product product = productDao.get(saleDto.getProductRef(), Product.class);
		Sales sales = SaleMapper.toSale(saleDto, product);
		return saleDao.save(sales);
	}

	@Override
	public boolean delete(Long id) {
		return saleDao.delete(id, Sales.class);
	}

	@Override
	public boolean update(SaleDto saleDto) {
		Product product = productDao.get(saleDto.getProductRef(), Product.class);
		Sales sale = SaleMapper.toSale(saleDto, product);
		return saleDao.update(sale);
	}

	@Override
	public Optional<SaleDto> get(Long id) {
		return Optional.ofNullable(saleDao.get(id, Sales.class))
				.map(SaleMapper::toSaleDto);
	}
}
