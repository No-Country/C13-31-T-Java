package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Bid;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import com.c1331tjava.ServiceApp.model.Work;
import com.c1331tjava.ServiceApp.repository.WorkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class WorkService {

    WorkRepository workRepository;

    UserEntityService userEntityService;
    public void save(Work work){
        try {
            workRepository.save(work);
        } catch (Exception e) {
            throw new CustomedHandler("Error persisting work");
        }
    }
}
