package com.groupeisi.com.company.services.produit;

import com.groupeisi.com.company.dao.product.IProductDao;
import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dao.user.IUserDao;
import com.groupeisi.com.company.dao.user.UserDao;
import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.UserEntity;
import com.groupeisi.com.company.mappers.ProductMapper;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {

	private final IProductDao productDao ;
	private final IUserDao userDao ;
	public ProductService() {
		this.userDao = new UserDao();
		this.productDao = new ProductDao();
	}
	@Override
	public List<ProductDto> getAll() {
		return ProductMapper.listProductToListProductDto(productDao.list(Product.class));
	}

	@Override
	public boolean save(ProductDto produitDto) {
		UserEntity user = userDao.get(produitDto.getUserEmail(), UserEntity.class);
		return productDao.save(ProductMapper.toProduct(produitDto,user));
	}

	@Override
	public boolean delete(String id) {
		return productDao.delete(id, Product.class);
	}

	@Override
	public boolean update(ProductDto productDto) {
		UserEntity user = userDao.get(productDto.getUserEmail(), UserEntity.class);
		return productDao.update(ProductMapper.toProduct(productDto, user));
	}

	@Override
	public Optional<ProductDto> get(String id) {
		return Optional.ofNullable(productDao.get(id, Product.class))
				.map(ProductMapper::toProductDto);
	}
}
