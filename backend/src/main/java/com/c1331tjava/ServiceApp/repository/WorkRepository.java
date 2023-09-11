package com.c1331tjava.ServiceApp.repository;

import com.c1331tjava.ServiceApp.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
}
