package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.model.Area;
import com.c1331tjava.ServiceApp.model.enums.AreasNames;

public interface I_AreaService {
    Area findByName(AreasNames name);
    Area findById(Long id);
}
