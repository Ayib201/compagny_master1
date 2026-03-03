package com.groupeisi.com.company.services.purchase;

import com.groupeisi.com.company.dao.product.IProductDao;
import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dao.purchase.IPurchaseDao;
import com.groupeisi.com.company.dao.purchase.PurchaseDao;
import com.groupeisi.com.company.dto.PurchaseDto;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Purchases;
import com.groupeisi.com.company.mappers.PurchaseMapper;

import java.util.List;
import java.util.Optional;

public class PurchaseService implements IPurchaseService {

	private final IProductDao productDao;
	private final IPurchaseDao dao = new PurchaseDao();

	public PurchaseService(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<PurchaseDto> getAll() {
		return PurchaseMapper.listPurchaseToListPurchaseDto(dao.list(Purchases.class));
	}

	@Override
	public boolean save(PurchaseDto purchaseDto) {
		Product product = productDao.get(purchaseDto.getProductRef(), Product.class);
		Purchases purchase = PurchaseMapper.toPurchase(purchaseDto, product);
		return dao.save(purchase);
	}

	@Override
	public boolean delete(Long id) {
		return dao.delete(id, Purchases.class);
	}

	@Override
	public boolean update(PurchaseDto purchaseDto) {
		Product product = productDao.get(purchaseDto.getProductRef(), Product.class);
		Purchases purchase = PurchaseMapper.toPurchase(purchaseDto, product);
		return dao.update(purchase);
	}

	@Override
	public Optional<PurchaseDto> get(Long id) {
		return Optional.ofNullable(dao.get(id, Purchases.class))
				.map(PurchaseMapper::toPurchaseDto);
	}
}