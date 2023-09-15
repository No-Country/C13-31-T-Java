package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Work;
import com.c1331tjava.ServiceApp.repository.WorkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkService {
    WorkRepository workRepository;
    public Work save(Work work){
        try {
            return workRepository.save(work);
        } catch (Exception e) {
            throw new CustomedHandler("Error persisting work");
        }
    }
    public Optional<Work> findById(Long id){
        try {
            return workRepository.findById(id);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing work database");
        }
    }
}
