package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.config.SecurityConfig;
import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OperationsService {
    WorkService workService;
    RequestService requestService;
    UserEntityService userEntityService;
    SecurityConfig securityConfig;
    NotificationService notificationService;

    public void selectBidAndCreateNewWork(Request request, Bid bid){

        //First create new Work using data from the request
        Work newWork = new Work(
                null,
                request.getClient(),
                bid.getProvider(),
                LocalDateTime.now(),
                null,
                bid,
                request,
                null,
                false,
                true
                );
        try {
            workService.save(newWork);
        } catch (Exception e) {
            throw new CustomedHandler("Error persisting new work");
        }

        //Second sets the request as ended
        request.setEnded(true);
        try {
            requestService.save(request);
        } catch (Exception e) {
            throw new CustomedHandler("Error updating request");
        }
        //Third: add notification to selected provider.
        notificationService.save(new Notification(
                null,
                "Han aceptado una de tus propuestas",
                LocalDateTime.now(),
                false)
        );
    }
    public UserEntity getAuthenticatedUser(){
        Optional<UserEntity> optU = null;
        try {
            optU = userEntityService.findByEmail(securityConfig.getUserNameFromToken());
        } catch (Exception e) {
            throw new CustomedHandler("Error fetching data from user database");
        }
        if (optU.isPresent()){
            return optU.get();
        } else throw new CustomedHandler("Error acquiring authenticated user");
    }


}
