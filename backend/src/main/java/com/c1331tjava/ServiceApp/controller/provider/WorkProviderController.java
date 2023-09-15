package com.c1331tjava.ServiceApp.controller.provider;

import com.c1331tjava.ServiceApp.dto.provider.workImagesDTO;
import com.c1331tjava.ServiceApp.model.ImagesR;
import com.c1331tjava.ServiceApp.model.ImagesW;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.model.Work;
import com.c1331tjava.ServiceApp.service.OperationsService;
import com.c1331tjava.ServiceApp.service.WorkService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/provider/work")
public class WorkProviderController {

    WorkService workService;
    OperationsService operationsService;

    @PostMapping("/images/{workId}")
    public ResponseEntity<?> addImages(@Valid @RequestBody workImagesDTO workImagesDTO, @PathVariable Long workId){

        UserEntity currentProvider = operationsService.getAuthenticatedUser();

        Work currentWork;
        if (workService.findById(workId).isPresent()) {
            currentWork = workService.findById(workId).get();
        } else {
            return new ResponseEntity<>("Work not found", HttpStatus.BAD_REQUEST);
        }

        if (currentProvider!=currentWork.getProvider()){
            return new ResponseEntity<>("Can´t add images to a work that is not yours", HttpStatus.FORBIDDEN);
        }

        Set<ImagesW> images = new HashSet<>();
        workImagesDTO.getImages().forEach(a -> images.add(new ImagesW(a)));
        currentWork.setImages(images);

        workService.save(currentWork);

        return new ResponseEntity<>("Images added to work", HttpStatus.OK);
    }

}
