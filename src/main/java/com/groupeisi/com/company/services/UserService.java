package com.groupeisi.com.company.services;

import com.groupeisi.com.company.dao.user.IUserDao;
import com.groupeisi.com.company.dao.user.UserDao;
import com.groupeisi.com.company.dto.UserDto;
import com.groupeisi.com.company.entities.UserEntity;
import com.groupeisi.com.company.mappers.UserMapper;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {

	private final IUserDao userDao = new UserDao();

	@Override
	public List<UserDto> getAll() {
		return UserMapper.listUserEntityToListUserDto(
				userDao.list(UserEntity.class)
		);
	}

	@Override
	public boolean save(UserDto userDto) {
		return userDao.save(UserMapper.toUserEtity(userDto));
	}

	@Override
	public Optional<UserDto> login(String email, String password) {
		return userDao.login(email, password)
				.map(UserMapper::toUserDto);
	}
}
