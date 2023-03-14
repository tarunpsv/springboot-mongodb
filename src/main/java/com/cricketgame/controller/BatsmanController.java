package com.cricketgame.controller;

import com.cricketgame.model.Batsman;
import com.cricketgame.service.BatsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class BatsmanController {

    @Autowired
    private BatsmanService batsmanService;

    @RequestMapping(method = RequestMethod.POST,value="/cricket/batsman")
    public Batsman createBatsman(@RequestBody Batsman batsman){
        return batsmanService.addBatsman(batsman);
    }

    @RequestMapping("/cricket/batsmen")
    public List<Batsman> getALLBatsmen(){
        return batsmanService.findAllBatsmen();
    }

    @RequestMapping("/cricket/batsman/{batsmanId}")
    public Batsman getBatsmanById(@PathVariable String batsmanId){
        return batsmanService.getBatsmanById(batsmanId);
    }

    @RequestMapping("/cricket/batsmen/{country}")
    public List<Batsman> getBatsmanByCountry(@PathVariable String country){
        return batsmanService.getBatsmenByCountry(country);
    }

    @RequestMapping(method = RequestMethod.PUT,value="/cricket/batsman")
    public Batsman modifyBatsman(@RequestBody Batsman batsman){
        return batsmanService.updateBatsman(batsman);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/cricket/batsman")
    public String deleteBatsmen(){
        return batsmanService.deleteBatsmen();
    }
}
