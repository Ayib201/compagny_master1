package com.groupeisi.com.company.services.produit;

import com.groupeisi.com.company.dao.product.IProductDao;
import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dao.user.IUserDao;
import com.groupeisi.com.company.dao.user.UserDao;
import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.dto.UserDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.UserEntity;
import com.groupeisi.com.company.mappers.ProductMapper;
import com.groupeisi.com.company.mappers.UserMapper;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {

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
