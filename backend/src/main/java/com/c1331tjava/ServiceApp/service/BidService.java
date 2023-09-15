package com.c1331tjava.ServiceApp.service;

import com.c1331tjava.ServiceApp.exception.CustomedHandler;
import com.c1331tjava.ServiceApp.model.Bid;
import com.c1331tjava.ServiceApp.repository.BidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BidService {

    BidRepository bidRepository;

    public Optional<Bid> findById(Long id){
        try {
            return this.bidRepository.findById(id);
        } catch (Exception e) {
            throw new CustomedHandler("Error fetching data from Bid database");
        }
    }

    public List<Bid> findAll(){
        try {
            return bidRepository.findAll();
        } catch (Exception e) {
            throw new CustomedHandler("Error fetching data from bid database");
        }
    }

    public Bid save(Bid bid){
        try {
            return bidRepository.save(bid);
        } catch (Exception e) {
            throw new CustomedHandler("Error persisting bid");
        }
    }
}
