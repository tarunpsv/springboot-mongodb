package com.cricketgame.service;

import com.cricketgame.model.Batsman;
import com.cricketgame.repository.BatsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class BatsmanService {
    @Autowired
    private BatsmanRepository batsmanRepository;

    public Batsman addBatsman(Batsman batsman){
        batsman.setBatsmanId(UUID.randomUUID().toString().split("-")[0]);
        return batsmanRepository.save(batsman);
    }

    public List<Batsman> findAllBatsmen(){
        return batsmanRepository.findAll();
    }

    public Batsman getBatsmanById(String batsmanId){
        return batsmanRepository.findById(batsmanId).get();
    }

    public List<Batsman> getBatsmenByCountry(String country){
        return batsmanRepository.findByBatsmanCountry(country);
    }

    public Batsman updateBatsman(Batsman batsman){
        Batsman oldBatsman=batsmanRepository.findById(batsman.getBatsmanId()).get();
        oldBatsman.setRunsScored(batsman.getRunsScored());
        oldBatsman.setBallsFaced(batsman.getBallsFaced());
        oldBatsman.setFours(batsman.getFours());
        oldBatsman.setSixes(batsman.getSixes());
        return batsmanRepository.save(oldBatsman);
    }

    public String deleteBatsmen(){
        batsmanRepository.deleteAll();
        return "Batsmen Data is Deleted";
    }
}
