package com.c1331tjava.ServiceApp.controller.client;

import com.c1331tjava.ServiceApp.model.Bid;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to handle Client side REST request of Bid.class
 */
@RestController
@RequestMapping("/api/v1/client/bid")
@AllArgsConstructor
public class BidClientController {

    BidService bidService;
    RequestService requestService;
    OperationsService operationsService;

    /**
     * Endpoint used when a client select one provider from the list of candidates.
     * It creates a new entry on Work table with the details of the work to do.
     *
     * @param bidId Represents the id of the bid to select
     * @param requestId
     * @return 200 Response in case of successful creation or exception if fails.
     */
    @Operation(summary = "Select the bid to create a new work",
            description = "<p>Only authorized to Client users.</p>" +
                    "<p>Must send the Bid id to accept and the Request id to which it belongs.</p>" +
                    "<p>Checks that the request is not ended.</p>" +
                    "<p>The operation must pass a verification of request ownership.</p>")
    @PostMapping("/select/{requestId}/{bidId}")
    public ResponseEntity<?> selectBid(@PathVariable Long requestId, @PathVariable Long bidId){

        UserEntity currentUser = operationsService.getAuthenticatedUser();

        //First verify if the Bid ID exist
        Bid currentBid;
        if (bidService.findById(bidId).isPresent()) {
            currentBid = bidService.findById(bidId).get();
        } else {
            return new ResponseEntity<>("Error: bidId not found", HttpStatus.BAD_REQUEST);
        }

        //Second checks that the bid request was posted by the authenticated user
        Request currentRequest;
        if (requestService.findById(requestId).isPresent()) {
            currentRequest = requestService.findById(requestId).get();
        } else {
            return new ResponseEntity<>("Error: requestId not found", HttpStatus.BAD_REQUEST);
        }

        if (currentRequest.getClient()!=currentUser)
            return new ResponseEntity<>("Can´t accept a bid of a request that´s not yours",HttpStatus.FORBIDDEN);

        //Fourth checks that the request is not marked as ended.
        if (currentRequest.getEnded())
            return new ResponseEntity<>("The request is already finished", HttpStatus.CONFLICT);

        //If everything goes well, create the new work
        operationsService.selectBidAndCreateNewWork(currentRequest, currentBid);
        return new ResponseEntity<>("Work created successfully", HttpStatus.CREATED);
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
