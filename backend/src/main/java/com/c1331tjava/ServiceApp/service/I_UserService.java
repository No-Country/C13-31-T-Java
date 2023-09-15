package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.dto.RegisterUserDto;
import com.c1331tjava.ServiceApp.model.UserEntity;

import java.util.Optional;

public interface I_UserService {
    RegisterUserDto save(RegisterUserDto user);
    Optional<UserEntity> findByEmail(String email);
}
