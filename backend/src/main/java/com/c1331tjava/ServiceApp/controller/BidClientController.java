package com.c1331tjava.ServiceApp.controller;

import com.c1331tjava.ServiceApp.config.SecurityConfig;
import com.c1331tjava.ServiceApp.dto.BidDTO;
import com.c1331tjava.ServiceApp.exception.CrossUserException;
import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Bid;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class to handle Client side REST request of Bid.class
 */
@RestController
@RequestMapping("/api/v1/client/bid")
@AllArgsConstructor
public class BidClientController {

    BidService bidService;
    UserEntityService userEntityService;
    RequestService requestService;
    OperationsService operationsService;
    @Autowired
    ModelMapper modelMapper;

    SecurityConfig securityConfig;

    /**
     * Endpoint to hande REST request of Bid details
     *
     * @param id: Id of requested bid details
     * @return BidDTO with details of a Bid filtered by Provider and Request
     */

    //TODO: Check if this endpoint is necessary

//    @Operation(summary = "Get Bid details by id",
//            description = "<p>Only authorized to Client users.</p>" +
//                    "<p>Extract query username from JWT.</p>" +
//                    "<p>A user can only query about bids of owned request. </p>")
//    @GetMapping("/details/{id}")
//    public BidDTO findByProviderAndRequest(@PathVariable Long id){
//        String currentUser = securityConfig.getUserNameFromToken();
//        Bid currentBid;
//        Request currentRequest;
        // Query Bid by Id and the Request it belongs
//        currentBid = this.bidService.findById(id).get();
//        currentRequest = this.requestService.findById(currentBid.getRequest().getId()).get();
        // If the user owner of the request differ to currentuser, throw cross user exception
//        if (!currentRequest.getClient().getEmail().equals(currentUser)){
//            throw new CrossUserException();
//        }
//        return modelMapper.map(currentBid, BidDTO.class);
//          return null;
//    }

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
            throw new CustomedHandler("Error: bidId not found");
        }

        //Second checks that the bid request was posted by the authenticated user
        Request currentRequest;
        if (requestService.findById(requestId).isPresent()) {
            currentRequest = requestService.findById(requestId).get();
        } else {
            throw new CustomedHandler("Error: requestId not found");
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
}
