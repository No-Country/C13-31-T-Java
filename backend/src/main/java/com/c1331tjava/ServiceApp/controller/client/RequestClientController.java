package com.c1331tjava.ServiceApp.controller.client;

import com.c1331tjava.ServiceApp.config.SecurityConfig;
import com.c1331tjava.ServiceApp.dto.client.RequestListPagedDTO;
import com.c1331tjava.ServiceApp.dto.client.RequestSaveDTO;
import com.c1331tjava.ServiceApp.model.ImagesR;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.model.enums.ZonesNames;
import com.c1331tjava.ServiceApp.service.OperationsService;
import com.c1331tjava.ServiceApp.service.RequestService;
import com.c1331tjava.ServiceApp.service.UserEntityService;
import com.c1331tjava.ServiceApp.service.ZoneService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class to handle Client side REST requests of Request.class
 */
@RestController
@RequestMapping("/api/v1/client/request")
@AllArgsConstructor
public class RequestClientController {

    RequestService requestService;
    SecurityConfig securityConfig;
    UserEntityService userEntityService;
    ZoneService zoneService;
    OperationsService operationsService;
    @Autowired
    ModelMapper modelMapper;

    /**
     * Endpoint to retrieve paged list of requests filter by Client(UserEntity).
     *
     * @param pageable page=[number of page]&size=[request per page]
     * @return RequestListPageDTO with totalPages, requestPerPage, totalRequest,
     * currentPage and List<RequestDTO>;
     */
    @Operation(summary = "Get paged Request list of a client",
            description = "<p>Only authorized to Client users.</p>" +
                    "<p>Extract query username from JWT</p>")
    @GetMapping("/list/paged")
    public RequestListPagedDTO findByClientAndActive(@ParameterObject Pageable pageable){

        UserEntity currentClient = operationsService.getAuthenticatedUser();

        Page<Request> RequestPaged = requestService.findByClientAndActiveTrue(currentClient, pageable);

        return new RequestListPagedDTO(RequestPaged);
    }

    /**
     * Endpoint to add a new request. Extracts user from token.
     *
     * @param requestSaveDTO The DTO containing basic data of request
     * @return ResponseEntity with a success message in case of successful creation, or an error message if creation fails.
     */
    @Operation(summary = "Persist a new request of the authenticated client",
            description = "<p>Only authorized to Client users.</p>" +
                    "<p>Require the basic request info</p>" +
                    "<p>The Date and client are automatic set")

    @PostMapping("/new")
    public ResponseEntity<?> save(@Valid @RequestBody RequestSaveDTO requestSaveDTO){

        UserEntity currentClient = operationsService.getAuthenticatedUser();

        Request request = modelMapper.map(requestSaveDTO, Request.class);

        // Sets the not auto mapped parameters
        request.setClient(currentClient);
        request.setDate(LocalDateTime.now());
        request.setZone(zoneService.findByName(ZonesNames.valueOf(requestSaveDTO.getZoneDTO())));
        request.setActive(true);
        request.setEnded(false);
        if (!requestSaveDTO.getImagesDTO().isEmpty()) {
            Set<ImagesR> images = new HashSet<>();
            requestSaveDTO.getImagesDTO().forEach(a -> images.add(new ImagesR(a)));
            request.setImages(images);
        }
        requestService.save(request);
        return new ResponseEntity<>("Request created suscesfully", HttpStatus.CREATED);
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
