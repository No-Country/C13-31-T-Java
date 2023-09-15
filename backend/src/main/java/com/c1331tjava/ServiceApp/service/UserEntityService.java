package com.c1331tjava.ServiceApp.service;

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
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing user database");
        }
    }
    public Optional<UserEntity> findByEmailAndActiveTrue(String email){
        try {
            return userRepository.findByEmailAndActiveTrue(email);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing user database");
        }
    }

    public UserEntity save(UserEntity userEntity){
        try {
            return userRepository.save(userEntity);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing user database");
        }
    }

    public Optional<UserEntity> findById(Long id){
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing user database");
        }
    }
}
