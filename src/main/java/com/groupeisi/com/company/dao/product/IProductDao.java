package com.groupeisi.com.company.dao.product;

import com.groupeisi.com.company.dao.Repository;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.UserEntity;

import java.util.Optional;


public interface IProductDao extends Repository<Product> {
    Product getProduct(String id);
}
