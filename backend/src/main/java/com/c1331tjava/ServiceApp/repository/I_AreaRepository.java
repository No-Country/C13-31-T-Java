package com.c1331tjava.ServiceApp.repository;

import com.c1331tjava.ServiceApp.model.Area;
import com.c1331tjava.ServiceApp.model.enums.AreasNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I_AreaRepository extends JpaRepository<Area, Long> {
    Area findByName(AreasNames name);
}
