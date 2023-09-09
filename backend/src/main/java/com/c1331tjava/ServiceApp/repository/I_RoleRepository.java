package com.c1331tjava.ServiceApp.repository;

import com.c1331tjava.ServiceApp.model.Role;
import com.c1331tjava.ServiceApp.model.enums.RolesNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository interface defines database operations for Role entities.
 */
@Repository
public interface I_RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name The name of the role to find.
     * @return The Role object if found, or null if not found.
     */
    Role findByName(RolesNames name);
}

