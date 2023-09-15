package com.c1331tjava.ServiceApp.controller.provider;

import com.c1331tjava.ServiceApp.dto.provider.NewBidDTO;
import com.c1331tjava.ServiceApp.model.Bid;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.service.BidService;
import com.c1331tjava.ServiceApp.service.OperationsService;
import com.c1331tjava.ServiceApp.service.RequestService;
import com.c1331tjava.ServiceApp.service.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class to handle Provider side REST request of Bid.class
 */
@RestController
@RequestMapping("/api/v1/provider/bid")
@AllArgsConstructor
public class BidProviderController {

    BidService bidService;
    UserEntityService userEntityService;
    RequestService requestService;
    OperationsService operationsService;
    @Autowired
    ModelMapper modelMapper;

    @Operation(summary = "Create a new postulation of provider to a request",
            description = "<p>Only authorized to Provider users.</p>" +
                    "<p>Require object with bid data</p>" +
                    "<p>The Date is automatic set")
    @PostMapping("/new/{requestId}")
    public ResponseEntity<?> newBid(@Valid @RequestBody NewBidDTO newBidDTO, @PathVariable Long requestId){

        UserEntity currentUser = operationsService.getAuthenticatedUser();

        Request currentRequest = requestService.findById(requestId).get();

        if (currentRequest.getProviders().contains(currentUser)){
            return new ResponseEntity<>("You already bid to this request", HttpStatus.CONFLICT);
        }

        Bid currentBid = modelMapper.map(newBidDTO, Bid.class);

        currentBid.setActive(true);
        currentBid.setProvider(currentUser);
        currentBid.setDate(LocalDateTime.now());
        currentBid.setProvider(currentUser);
        bidService.save(currentBid);
        Set<Bid> tempBids = new HashSet<>(currentRequest.getBids());
        tempBids.add(currentBid);
        currentRequest.setBids(tempBids);
        Set<UserEntity> tempProviders = new HashSet<>(currentRequest.getProviders());
        tempProviders.add(currentUser);
        currentRequest.setProviders(tempProviders);

        requestService.save(currentRequest);

        return new ResponseEntity<>("Bid created successfully", HttpStatus.CREATED);
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
