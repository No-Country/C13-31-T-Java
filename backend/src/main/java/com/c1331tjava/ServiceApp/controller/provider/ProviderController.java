package com.c1331tjava.ServiceApp.controller.provider;

import com.c1331tjava.ServiceApp.config.SecurityConfig;
import com.c1331tjava.ServiceApp.dto.provider.ProviderDTO;
import com.c1331tjava.ServiceApp.dto.provider.UpdateProviderDto;
import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Area;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.model.enums.AreasNames;
import com.c1331tjava.ServiceApp.service.AreaServiceImpl;
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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/provider")
@AllArgsConstructor
public class ProviderController {

    AreaServiceImpl areaService;
    SecurityConfig securityConfig;
    UserEntityService userEntityService;
    @Autowired
    PasswordEncoder passwordEncoder;
    OperationsService operationsService;
    @Autowired
    ModelMapper modelMapper;

    /**
     * Endpoint to retrieve Provider details using username extracted from token
     *
     * @return User details
     */
    @Operation(summary = "Get Provider details by its username(email)",
            description = "<p>Only authorized to Provider users.</p>" +
                    "<p>Extract query username from JWT</p>")
    @GetMapping("/details")
    public ProviderDTO findByEmail() {

        UserEntity currentUser = operationsService.getAuthenticatedUser();
        return modelMapper.map(currentUser, ProviderDTO.class);
    }


    /**
     * Endpoint to update client(UserEntity) details
     *
     * @param updateProviderDto
     * @return If everything goes well ResponseEntity.ok, otherwise throw exception
     */

    @Operation(summary = "Update Provider user details.",
            description = "<p>Only authorized to Provider users.</p>" +
                    "<p>Extract username from JWT and actualizes details with body object</p>" +
                    "<p>Is not required to send all details, only ones that wants to change")

    @PostMapping("/update")
    public ResponseEntity<?> updateProvider(@Valid @RequestBody UpdateProviderDto updateProviderDto){

        UserEntity currentUser = operationsService.getAuthenticatedUser();

        if (!(updateProviderDto.getUserName()==null)){currentUser.setUserName(updateProviderDto.getUserName());}
        if (!(updateProviderDto.getUserLastname()==null)){currentUser.setUserLastname(updateProviderDto.getUserLastname());}
        if (!(updateProviderDto.getPassword()==null)){currentUser.setPassword(passwordEncoder.encode(updateProviderDto.getPassword()));}
        if (!(updateProviderDto.getTe()==null)){currentUser.setTe(updateProviderDto.getTe());}
        if (!(updateProviderDto.getAreas()==null)){
            List<Area> areas = new ArrayList<>();
            updateProviderDto.getAreas().forEach(a -> areas.add(areaService.findByName(AreasNames.valueOf(a))));
            currentUser.setAreas(areas);
        }
        try {
            this.userEntityService.save(currentUser);
        } catch (Exception e) {
            throw new CustomedHandler("Error saving user");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

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
