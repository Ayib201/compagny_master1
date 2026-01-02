package com.groupeisi.com.company.services.sale;

import com.groupeisi.com.company.dao.product.IProductDao;
import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.mappers.ProductMapper;

import java.util.List;

public class SaleService implements ISaleService {

	private final IProductDao productDao = new ProductDao();
	@Override
	public List<ProductDto> getAll() {
		return ProductMapper.listProductToListProductDto(productDao.list(Product.class));
	}

	@Override
	public boolean save(ProductDto produitDto) {
		return productDao.save(ProductMapper.toProduct(produitDto));
	}
}
