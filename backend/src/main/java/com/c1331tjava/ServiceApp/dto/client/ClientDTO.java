package com.c1331tjava.ServiceApp.dto.client;

import com.c1331tjava.ServiceApp.model.Notification;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {
    private String imageLink;
    private String userName;
    private String userLastname;
    @NotNull (message = "Username must not be null")
    private String email;
    private String te;
//    private List<Notification> notifications;
}
