package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Role;
import com.c1331tjava.ServiceApp.model.enums.RolesNames;
import com.c1331tjava.ServiceApp.repository.I_RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service class provides methods to manage roles and related operations.
 */
@Service
public class RoleServiceImpl implements I_RoleService {

    @Autowired
    I_RoleRepository roleRepository;

    /**
     * Finds a role by its name.
     *
     * @param name The name of the role to be found.
     * @return The found role.
     */
    @Override
    public Role findByName(RolesNames name) {
        try {
            return roleRepository.findByName(name);
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing role database");
        }
    }

    /**
     * Finds a role by its Id
     *
     * @param id
     * @return The Found role
     */
    public Role findById (Long id){
        try {
            return roleRepository.findById(id).get();
        } catch (Exception e) {
            throw new CustomedHandler("Error accessing role database");
        }
    }
}

