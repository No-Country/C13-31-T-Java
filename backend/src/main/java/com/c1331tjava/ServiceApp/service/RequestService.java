package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.model.Zone;
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
        try {
            return requestRepository.findByClientAndActiveTrue(userEntity, pageable);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing user database");
        }

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
    public Request save(Request request){
        try {
            return requestRepository.save(request);
        } catch (Exception e) {
            throw new CustomedHandler("Error persisting data in request database");
        }
    }
    public Page<Request> findByDescriptionContainingAndEndedFalseOrderByDate(String criteria, Pageable pageable){
        try {
            return requestRepository.findByDescriptionContainingAndEndedFalseOrderByDate(criteria, pageable);
        } catch (Exception e) {
            throw new CustomedHandler("Error searching request database");
        }
    }
    public Page<Request> findByDescriptionContainingAndZoneAndEndedFalseOrderByDate(String criteria, Zone zone, Pageable pageable){
        try {
            return requestRepository
                    .findByDescriptionContainingAndZoneAndEndedFalseOrderByDate(
                            criteria, zone, pageable);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing request database");
        }
    }
}
