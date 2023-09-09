package com.c1331tjava.ServiceApp.controller;

import com.c1331tjava.ServiceApp.dto.LoginUserDto;
import com.c1331tjava.ServiceApp.dto.RegisterUserDto;
import com.c1331tjava.ServiceApp.exception.UserAlreadyExistException;
import com.c1331tjava.ServiceApp.model.Area;
import com.c1331tjava.ServiceApp.model.Notification;
import com.c1331tjava.ServiceApp.model.Role;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.model.enums.AreasNames;
import com.c1331tjava.ServiceApp.model.enums.RolesNames;
import com.c1331tjava.ServiceApp.security.filter.JwtUtil;
import com.c1331tjava.ServiceApp.service.AuthServiceImpl;
import com.c1331tjava.ServiceApp.service.I_UserService;
import com.c1331tjava.ServiceApp.service.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Esta clase implementa pruebas unitarias para el controlador de autenticación {@link AuthController}.
 * Utiliza el framework Spring Boot y la biblioteca MockMvc para simular solicitudes HTTP.
 */
@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private AuthController authController;
    @MockBean
    private I_UserService userService;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private AuthenticationManager authenticationManager;
    private final RegisterUserDto registerUserDto=new RegisterUserDto();
    private final LoginUserDto loginUserDto = new LoginUserDto();
    @BeforeEach
    void setUp() {
        registerUserDto.setUserName("Sebastian");
        registerUserDto.setTe("243242341");
        registerUserDto.setUserLastname("Insua");
        registerUserDto.setRoles(createRoles());
        registerUserDto.setPassword("sebas10capo");
        registerUserDto.setEmail("sebastian@gmail.com");
        //login dto
        loginUserDto.setEmail("sebastian@gmail.com");
        loginUserDto.setPassword("sebas10capo");
    }
    private List<Long> createRoles() {
        Long role1= 2L;
        return new ArrayList<>(List.of(role1));
    }
    private List<Area> createAreas(){
        Area area1= new Area(1l,AreasNames.Masonry);
        return new ArrayList<>(List.of(area1));
    }

    @Test
    void login_Success() {
        // Given
        // Mock the behavior of authenticationManager's authenticate method
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword());
        when(authenticationManager.authenticate(authentication)).thenReturn(authentication);
        // Mock the behavior of jwtUtil's generateAccessToken method
        when(jwtUtil.generateAccesToken(loginUserDto.getEmail())).thenReturn("generated_token");
        // When
        ResponseEntity<?> responseEntity = authController.login(loginUserDto);
        // Then
        // Verify that the response status is HttpStatus.OK (200)
        // You can also check the response body or other aspects of the response if necessary
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void login_BadCredentials() {
        // Given
        // Mock the behavior of authenticationManager's authenticate method to throw BadCredentialsException
        when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);
        // When
        ResponseEntity<?> responseEntity = authController.login(loginUserDto);
        // Then
        // Verify that the response status is HttpStatus.UNAUTHORIZED (401)
        // You can also check the response body or other aspects of the response if necessary
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    void createUser() {
        //given - configuration
        // Mock the behavior of the userService's save method
        when(userService.save(registerUserDto)).thenReturn(registerUserDto);
        //when - comportamiento a testear
        ResponseEntity<?> responseEntity = authController.createUser(registerUserDto);
        //then - verificar salida
        // Verify that the response status is HttpStatus.CONFLICT (409)
        // You can also check the response body or other aspects of the response if necessary
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    @Test
    void createUser_UserAlreadyExists() {
        // Given
        RegisterUserDto registerUserDto = new RegisterUserDto(/* initialize with required data */);

        // Mock the behavior of the userService's save method to throw UserAlreadyExistException
        when(userService.save(registerUserDto)).thenThrow(UserAlreadyExistException.class);

        // When
        ResponseEntity<?> responseEntity = authController.createUser(registerUserDto);

        // Then
        // Verify that the response status is HttpStatus.CONFLICT (409)
        // You can also check the response body or other aspects of the response if necessary
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }
}