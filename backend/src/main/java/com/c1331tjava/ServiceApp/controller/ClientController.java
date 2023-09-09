package com.c1331tjava.ServiceApp.controller;

import com.c1331tjava.ServiceApp.dto.client.ClientDTO;
import com.c1331tjava.ServiceApp.dto.client.UpdateClientDto;
import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.service.OperationsService;
import com.c1331tjava.ServiceApp.service.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Class to handle Client side REST requests of UserEntity.class
 */
@RestController
@RequestMapping("/api/v1/client")
@AllArgsConstructor
public class ClientController {

    OperationsService operationsService;
    UserEntityService userEntityService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;


    /**
     * Endpoint to retrieve Client details using username extracted from token
     *
     * @return User details
     */
    @Operation(summary = "Get Client details by its username(email)",
            description = "<p>Only authorized to Client users.</p>" +
                    "<p>Extract query username from JWT</p>")
    @GetMapping("/details")
    public ClientDTO findByEmail(){

        UserEntity currentUser = operationsService.getAuthenticatedUser();
        return modelMapper.map(currentUser, ClientDTO.class);

    }

    /**
     * Endpoint to update client(UserEntity) details
     *
     * @param updateClientDto
     * @return If everything goes well ResponseEntity.ok, otherwise throw exception
     */

    @Operation(summary = "Update Client user details.",
            description = "<p>Only authorized to Client users.</p>" +
                    "<p>Extract username from JWT and actualizes details with body object</p>" +
                    "<p>Is not required to send all details, only ones that wants to change")

    @PostMapping("/update")
    public ResponseEntity<?> updateClient(@Valid @RequestBody UpdateClientDto updateClientDto){

        UserEntity currentUser = operationsService.getAuthenticatedUser();

        if (!(updateClientDto.getUserName()==null)){currentUser.setUserName(updateClientDto.getUserName());}
        if (!(updateClientDto.getUserLastname()==null)){currentUser.setUserLastname(updateClientDto.getUserLastname());}
        if (!(updateClientDto.getPassword()==null)){currentUser.setPassword(passwordEncoder.encode(updateClientDto.getPassword()));}
        if (!(updateClientDto.getTe()==null)){currentUser.setTe(updateClientDto.getTe());}
        try {
            this.userEntityService.save(currentUser);
        } catch (Exception e) {
            throw new CustomedHandler("Error saving user");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
