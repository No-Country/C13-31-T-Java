package com.c1331tjava.ServiceApp.service;


import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Zone;
import com.c1331tjava.ServiceApp.model.enums.ZonesNames;
import com.c1331tjava.ServiceApp.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ZoneService {

    ZoneRepository zoneRepository;
    public Zone findByName(ZonesNames name){
        try {
            return this.zoneRepository.findByName(name);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing zone database");
        }
    }

}
