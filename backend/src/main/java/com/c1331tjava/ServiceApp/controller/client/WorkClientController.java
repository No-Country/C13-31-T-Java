package com.c1331tjava.ServiceApp.controller.client;

import com.c1331tjava.ServiceApp.dto.client.WorkFinishDTO;
import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.*;
import com.c1331tjava.ServiceApp.repository.RatingRepository;
import com.c1331tjava.ServiceApp.service.OperationsService;
import com.c1331tjava.ServiceApp.service.RatingService;
import com.c1331tjava.ServiceApp.service.UserEntityService;
import com.c1331tjava.ServiceApp.service.WorkService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/client/work")
public class WorkClientController {

    WorkService workService;
    RatingService ratingService;
    OperationsService operationsService;

    @PostMapping("/finish/{workId}")
    public ResponseEntity<?> workFinish(@Valid @RequestBody WorkFinishDTO workFinishDTO, @PathVariable Long workId){

        //First retrieves the work from database
        Work currentWork = new Work();
        if (workService.findById(workId).isPresent()){
            currentWork = workService.findById(workId).get();
        } else {
            return new ResponseEntity<>("Error: work not found", HttpStatus.BAD_REQUEST);
        }

        //Second checks that the works belongs to the authenticated user
        UserEntity currentUser = operationsService.getAuthenticatedUser();
        if (currentUser!=currentWork.getClient()){
            return new ResponseEntity<>("Can´t finish a work that´s not yours", HttpStatus.FORBIDDEN);
        }

        currentWork.setEnded(true);
        currentWork.setEndDate(LocalDateTime.now());

        // If DTO has images, adds it to work
        if (!workFinishDTO.getImages().isEmpty()) {
            Set<ImagesW> images = new HashSet<>();
            workFinishDTO.getImages().forEach(a -> images.add(new ImagesW(a)));
            currentWork.setImages(images);
        }

        workService.save(currentWork);

        Rating currentRating = new Rating();

        currentRating.setClient(currentWork.getClient());
        currentRating.setProvider(currentWork.getProvider());
        currentRating.setComment(workFinishDTO.getComment());
        currentRating.setValue(workFinishDTO.getQualification());
        currentRating.setWork(currentWork);

        ratingService.save(currentRating);



        return new ResponseEntity<>("Work finished, and qualification saved", HttpStatus.CREATED);
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
