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
		Product product = productDao.getProduct(purchaseDto.getProduct_ref());
		// Convertir en entité Purchases avec product
		Purchases purchase = PurchaseMapper.toPurchase(purchaseDto, product);
		return dao.save(purchase);
	}
}
