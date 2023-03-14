package com.cricketgame.controller;

import com.cricketgame.model.Batsman;
import com.cricketgame.model.Bowler;
import com.cricketgame.service.BowlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class BowlerController {

    @Autowired
    private BowlerService bowlerService;

    @PostMapping("/cricket/bowler")
    public Bowler createBowler(@RequestBody Bowler bowler){
        return bowlerService.addBowler(bowler);
    }

    @GetMapping("/cricket/bowlers")
    public List<Bowler> getAllBowlers(){
        return bowlerService.findAllBowlers();
    }

    @GetMapping("cricket/bowler/{bowlerId}")
    public Bowler getBowlerById(@PathVariable String bowlerId){
        return bowlerService.findBowlerById(bowlerId);
    }

    @GetMapping("cricket/bowlers/{country}")
    public List<Bowler> getBowlersByCountry(@PathVariable String country){
        return bowlerService.findBowlerByCountry(country);
    }

    @PutMapping("cricket/bowler")
    public Bowler modifyBowler(@RequestBody Bowler bowler){
        return bowlerService.updateBowler(bowler);
    }

    @DeleteMapping("/cricket/bowler")
    public String deleteBowlers(){
        return bowlerService.deleteBowlers();
    }
}
