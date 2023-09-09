package com.c1331tjava.ServiceApp.repository;

import com.c1331tjava.ServiceApp.model.Zone;
import com.c1331tjava.ServiceApp.model.enums.ZonesNames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone findByName (ZonesNames string);
}
