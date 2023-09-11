package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.config.SecurityConfig;
import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.repository.I_UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserEntityService {

    I_UserRepository userRepository;

    public Optional<UserEntity> findByEmail(String email){

            return userRepository.findByEmail(email);

    }
    public Optional<UserEntity> findByEmailAndActiveTrue(String email){

        return userRepository.findByEmailAndActiveTrue(email);

    }

    public void save(UserEntity userEntity){

            this.userRepository.save(userEntity);
    }

    public Optional<UserEntity> findById(Long id){
            return this.userRepository.findById(id);
    }
}
