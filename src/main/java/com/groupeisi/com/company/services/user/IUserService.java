package com.groupeisi.com.company.services.user;

import com.groupeisi.com.company.dto.UserDto;

import java.util.List;
import java.util.Optional;


public interface IUserService {

	List<UserDto> getAll();
	boolean save(UserDto userDto);
	Optional<UserDto> login (String email, String password);
}
