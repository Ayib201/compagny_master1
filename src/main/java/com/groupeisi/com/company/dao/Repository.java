package com.groupeisi.com.company.dao;

import java.util.List;

public interface Repository <T, I> {
	boolean save(T t);
	boolean delete(I id, Class<T> clazz);
	boolean update(T t);
	List<T> list(Class<T> clazz);
	T get(I id, Class<T> clazz);
	List<T> listpaginate(Class<T> clazz, int page, int pageSize);
}
