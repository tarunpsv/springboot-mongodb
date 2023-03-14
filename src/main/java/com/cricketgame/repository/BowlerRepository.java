package com.cricketgame.repository;

import com.cricketgame.model.Bowler;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BowlerRepository extends MongoRepository<Bowler,String> {
    List<Bowler> findByBowlerCountry(String country);
}
