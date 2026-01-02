package com.groupeisi.com.company.dao;

import com.groupeisi.com.company.entities.UserEntity;

import java.util.List;

public interface Repository <T> {
	boolean save(T t);
	boolean delete(long id, Class<T> clazz);
	boolean update(T t);
	List<T> list(Class<T> clazz);
	T get(long id, Class<T> clazz);
	List<T> listpaginate(Class<T> clazz, int page, int pageSize);
}
