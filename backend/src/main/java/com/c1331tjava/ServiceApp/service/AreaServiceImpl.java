package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Area;
import com.c1331tjava.ServiceApp.model.enums.AreasNames;
import com.c1331tjava.ServiceApp.repository.I_AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements I_AreaService {
    @Autowired
    I_AreaRepository areaRepository;
    @Override
    public Area findByName(AreasNames name) {
        try {
            return areaRepository.findByName(name);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing area database");
        }
    }

    public Area findById(Long id){
        try {
            return areaRepository.findById(id).get();
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing area database");
        }
    }

    public List<Area> findAll(){
        try {
            return areaRepository.findAll();
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing area database");
        }
    }
}
