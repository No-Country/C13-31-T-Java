package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.model.Role;
import com.c1331tjava.ServiceApp.model.enums.RolesNames;
import org.springframework.stereotype.Service;

public interface I_RoleService  {
    Role findByName(RolesNames name);
    Role findById(Long id);
}
