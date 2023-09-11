package com.c1331tjava.ServiceApp.controller;

import com.c1331tjava.ServiceApp.dto.provider.RequestListPagedDTO;
import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.service.OperationsService;
import com.c1331tjava.ServiceApp.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/provider/request")
@AllArgsConstructor
public class RequestProviderController {

    OperationsService operationsService;
    RequestService requestService;

    /**
     *
     * @param pageable
     *
     * @return
     */
    @Operation(summary = "Get paged list of requests that has postulated",
            description = "<p>Only authorized to Provider users.</p>" +
                    "<p>Extract query username from JWT</p>")
    @GetMapping("/postuled/paged")
    public RequestListPagedDTO findByProvider(@ParameterObject Pageable pageable){

        UserEntity currentUser = operationsService.getAuthenticatedUser();

        Page<Request> RequestPaged = null;
        try {
            RequestPaged = requestService.findByProvidersContaining(currentUser, pageable);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing request database");
        }
        return new RequestListPagedDTO(RequestPaged);
    }
}
