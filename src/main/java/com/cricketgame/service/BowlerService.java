package com.cricketgame.service;

import com.cricketgame.model.Bowler;
import com.cricketgame.repository.BowlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class BowlerService {

    @Autowired
    private BowlerRepository bowlerRepository;

    public Bowler addBowler(Bowler bowler){
        bowler.setBowlerId(UUID.randomUUID().toString().split("-")[0]);
        return bowlerRepository.save(bowler);
    }

    public List<Bowler> findAllBowlers(){
        return bowlerRepository.findAll();
    }

    public Bowler findBowlerById(String bowlerId){
        return bowlerRepository.findById(bowlerId).get();
    }

    public List<Bowler> findBowlerByCountry(String country){
        return bowlerRepository.findByBowlerCountry(country);
    }

    public Bowler updateBowler(Bowler bowler){
        Bowler oldBowler=bowlerRepository.findById(bowler.getBowlerId()).get();
        oldBowler.setRunsGiven(bowler.getRunsGiven());
        oldBowler.setOverBowled(bowler.getOverBowled());
        oldBowler.setWicketsTaken(bowler.getWicketsTaken());
        oldBowler.setLeftOverBalls(bowler.getLeftOverBalls());
        oldBowler.setExtras(bowler.getExtras());
        return bowlerRepository.save(oldBowler);
    }

    public String deleteBowlers(){
        bowlerRepository.deleteAll();
        return "Bowlers Data is Deleted";
    }
}
