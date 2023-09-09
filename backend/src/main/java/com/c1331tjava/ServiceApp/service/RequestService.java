package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Bid;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestService {

    RequestRepository requestRepository;

    /**
     * Method to find active Request by Client(type: UserEntity)
     *
     * @param userEntity Client extracted from token on controller
     * @param pageable page=[number of page]&size=[request per page]
     * @return Page<Request> Paged query result
     */
    public Page<Request> findByClientAndActiveTrue(UserEntity userEntity, Pageable pageable){

            return requestRepository.findByClientAndActiveTrue(userEntity, pageable);

    }

    public Optional<Request> findById(Long id) {
        try {
            return requestRepository.findById(id);
        } catch (Exception e) {
            throw new CustomedHandler("Error fetching data from request database");
        }
    }

    public List<Request> findAll(){

        try {
            return requestRepository.findAll();
        } catch (Exception e) {
            throw new CustomedHandler("Error fetching data from request database");
        }
    }

    public Page<Request> findByProvidersContaining(UserEntity userEntity, Pageable pageable){
        try {
            return requestRepository.findByProvidersContaining(userEntity, pageable);
        } catch (Exception e) {
            throw new CustomedHandler("Error fetching data from request database");
        }
    }
    public void save(Request request){

        try {
            requestRepository.save(request);
        } catch (Exception e) {
            throw new CustomedHandler("Error persisting data in request database");
        }
    }
}
