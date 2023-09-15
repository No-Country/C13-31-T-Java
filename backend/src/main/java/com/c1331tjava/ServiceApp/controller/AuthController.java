package com.c1331tjava.ServiceApp.controller;

import com.c1331tjava.ServiceApp.dto.LoginResponseDTO;
import com.c1331tjava.ServiceApp.dto.LoginResponseUserDTO;
import com.c1331tjava.ServiceApp.dto.LoginUserDto;
import com.c1331tjava.ServiceApp.dto.RegisterUserDto;
import com.c1331tjava.ServiceApp.exception.UserAlreadyExistException;
import com.c1331tjava.ServiceApp.model.Role;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.security.filter.JwtUtil;
import com.c1331tjava.ServiceApp.service.I_UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents the REST controller for handling authentication and user registration.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {

    @Autowired
    private I_UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Endpoint for user login.
     *
     * @param loginUserDto The data transfer object containing user login credentials.
     * @return ResponseEntity with a JWT token in case of successful authentication, or an error message if authentication fails.
     */
    @Operation(summary = "Used to user login",
            description = "<p>No authentication required</p>" +
                    "<p>If credentials are ok, returns a valid token</p>")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserDto loginUserDto) {
        try {
            // Authenticate the user using provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserDto.getEmail(),
                            loginUserDto.getPassword()
                    )
            );

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtil.generateAccesToken(loginUserDto.getEmail());

            // Return JWT token and user details in the response
            UserEntity currentUser = userService.findByEmail(loginUserDto.getEmail()).get();
            LoginResponseUserDTO loginResponseUserDTO = new LoginResponseUserDTO(
                    currentUser.getUserName(),
                    currentUser.getUserLastname(),
                    currentUser.getRoles().stream().map(role -> String.valueOf(role.getName())).toList()
            );
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                    token,
                    loginResponseUserDTO
            );
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            // Return an error response if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    /**
     * Endpoint for user registration.
     *
     * @param registerUserDto The data transfer object containing user registration details.
     * @return ResponseEntity with a success message in case of successful registration, or an error message if registration fails.
     */
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        try {
            userService.save(registerUserDto);
        } catch (UserAlreadyExistException ue) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", "This user already exists"));
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }


    /**
     *
     * Class to handle @Valid exception and personalize error message
     *
     * @param ex type: MethodArgumentNotValidException, exception thrown by @Valid
     * @return Personalized error message
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = "Bad Request";
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

