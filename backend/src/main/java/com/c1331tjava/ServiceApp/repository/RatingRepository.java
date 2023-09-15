package com.c1331tjava.ServiceApp.repository;

import com.c1331tjava.ServiceApp.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
