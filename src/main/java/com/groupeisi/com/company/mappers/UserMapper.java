package com.groupeisi.com.company.mappers;

import com.groupeisi.com.company.dto.UserDto;
import com.groupeisi.com.company.entities.UserEntity;

import java.util.List;

public class UserMapper {

    private UserMapper() {

    }

    public static List<UserDto> listUserEntityToListUserDto(List<UserEntity> users) {

        return users.stream()
            .map(UserMapper::toUserDto)
            .toList();
    }

    public static UserDto toUserDto(UserEntity user) {

        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(),  user.getPassword());
    }

    public static UserEntity toUserEtity(UserDto user) {

        return new UserEntity(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword());
    }
}
