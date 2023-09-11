package com.c1331tjava.ServiceApp.repository;

import com.c1331tjava.ServiceApp.model.Bid;
import com.c1331tjava.ServiceApp.model.Request;
import com.c1331tjava.ServiceApp.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    Page<Request> findByClientAndActiveTrue(UserEntity userEntity, Pageable pageable);

    Page<Request> findByProvidersContaining(UserEntity userEntity, Pageable pageable);

}
