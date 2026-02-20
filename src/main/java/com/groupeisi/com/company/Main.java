package com.groupeisi.com.company;

import com.groupeisi.com.company.dto.UserDto;
import com.groupeisi.com.company.services.user.UserService;

import java.util.logging.Logger;

public class Main implements Runnable {
    Logger logger = Logger.getLogger(getClass().getName());
    @Override
    public void run() {
        UserDto userDto = UserDto.builder()
                .firstName("Ayib")
                .lastName("Toure")
                .email("ayib@gmail.com")
                .password("passer")
                .build();

        UserService us = new UserService();
        boolean result = us.save(userDto);

        if (result) {
            logger.info("User saved successfully");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Main());
        thread.start();
    }
}