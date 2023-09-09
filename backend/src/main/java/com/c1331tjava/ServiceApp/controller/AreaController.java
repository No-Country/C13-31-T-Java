package com.c1331tjava.ServiceApp.controller;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Area;
import com.c1331tjava.ServiceApp.service.AreaServiceImpl;
import com.c1331tjava.ServiceApp.service.I_AreaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/area")
public class AreaController {

    @Autowired
    private AreaServiceImpl areaService;

    @GetMapping("/list")
    public List<Area> findAll(){
        try {
            return this.areaService.findAll();
        } catch (Exception e) {
            throw new CustomedHandler("Error retrieving data from areas database");
        }
    }
}
