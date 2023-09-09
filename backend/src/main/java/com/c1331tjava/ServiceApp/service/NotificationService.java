package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Notification;
import com.c1331tjava.ServiceApp.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    NotificationRepository notificationRepository;
    public void save(Notification notification){

        try {
            notificationRepository.save(notification);
        } catch (Exception e) {
            throw new CustomedHandler("Error persisting notification");
        }

    }
}
