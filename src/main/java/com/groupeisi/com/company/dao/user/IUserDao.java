package com.groupeisi.com.company.dao.user;

import com.groupeisi.com.company.dao.Repository;
import com.groupeisi.com.company.entities.UserEntity;

import java.util.Optional;


public interface IUserDao extends Repository<UserEntity> {

	Optional<UserEntity> login (String email, String password);
}
