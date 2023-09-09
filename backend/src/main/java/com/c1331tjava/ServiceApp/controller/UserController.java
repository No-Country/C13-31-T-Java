package com.c1331tjava.ServiceApp.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('Admin')")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
//    @GetMapping
//    public ResponseEntity<?> findAllUsers(){
//        return new ResponseEntity<>();
//    }
}
