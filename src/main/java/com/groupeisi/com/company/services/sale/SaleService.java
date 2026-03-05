package com.groupeisi.com.company.services.sale;

import com.groupeisi.com.company.dao.product.IProductDao;
import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dao.sale.ISaleDao;
import com.groupeisi.com.company.dao.sale.SaleDao;
import com.groupeisi.com.company.dao.user.IUserDao;
import com.groupeisi.com.company.dao.user.UserDao;
import com.groupeisi.com.company.dto.SaleDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Sales;
import com.groupeisi.com.company.entities.UserEntity;
import com.groupeisi.com.company.mappers.SaleMapper;

import java.util.List;
import java.util.Optional;

public class SaleService implements ISaleService {
	private final IProductDao productDao;
	private final IUserDao userDao;
	private final ISaleDao saleDao = new SaleDao();

	public SaleService() {
		this.productDao = new ProductDao();
		this.userDao = new UserDao();
	}

	@Override
	public List<SaleDto> getAll() {
		return SaleMapper.listSaleToListSaleDto(saleDao.list(Sales.class));
	}

	@Override
	public boolean save(SaleDto saleDto) {
		Product product = productDao.get(saleDto.getProductRef(), Product.class);
		UserEntity user = userDao.get(saleDto.getUserEmail(), UserEntity.class);
		Sales sales = SaleMapper.toSale(saleDto, product, user);
		return saleDao.save(sales);
	}

	@Override
	public boolean delete(Long id) {
		return saleDao.delete(id, Sales.class);
	}

	@Override
	public boolean update(SaleDto saleDto) {
		Product product = productDao.get(saleDto.getProductRef(), Product.class);
		UserEntity user = userDao.get(saleDto.getUserEmail(), UserEntity.class);
		Sales sale = SaleMapper.toSale(saleDto, product, user);
		return saleDao.update(sale);
	}

	@Override
	public Optional<SaleDto> get(Long id) {
		return Optional.ofNullable(saleDao.get(id, Sales.class))
				.map(SaleMapper::toSaleDto);
	}
}
