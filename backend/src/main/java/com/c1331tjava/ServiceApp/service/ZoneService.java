package com.c1331tjava.ServiceApp.service;


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

        return this.zoneRepository.findByName(name);

    }

}
