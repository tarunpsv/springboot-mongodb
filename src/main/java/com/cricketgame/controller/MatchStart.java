package com.cricketgame.controller;

import com.cricketgame.model.Batsman;
import com.cricketgame.model.Bowler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@Component
public class MatchStart {
    @Autowired
    private BatsmanController batsmanController;
    @Autowired
    private BowlerController bowlerController;

    public int match(String teamOne, String teamTwo,int players, int totalOvers, int target){
        int totalScore=0;
        int runsOnThisBall=0;
        Batsman batsmanOne=new Batsman("00","Batsman1",teamOne,0,0,0,0);
        batsmanController.createBatsman(batsmanOne);
        Batsman batsmanTwo=new Batsman("00","Batsman2",teamOne,0,0,0,0);
        batsmanController.createBatsman(batsmanTwo);
        int batsmen=2;
        int striking=0;
        Bowler bowler=new Bowler("00","Bowler1",teamTwo,0,0,0,0,0);
        bowlerController.createBowler(bowler);
        int bowlers=0;
        int ballsDoneInThisOver=1;
        int oversTillNow=0;
        int totalWickets=0;
        boolean freeHit=false;
        HashMap<Integer,Bowler> bowlersList=new HashMap<Integer, Bowler>();
        bowlersList.put(bowlers+1,bowler);
//        System.out.println("The batting team is : "+teamOne);
//        System.out.println("The bowling team is : "+teamTwo);
        while((oversTillNow < totalOvers) && (totalScore < target) && (totalWickets<players-1)){
            runsOnThisBall=(int)(Math.random()*100)%11;
            if(bowlersList.containsKey(bowlers+1))
            {
                bowler = bowlersList.get(bowlers+1);
            }
            else {
                bowler=new Bowler("00","Bowler"+(bowlers+1),teamTwo,0,0,0,0,0);
                bowlerController.createBowler(bowler);
                bowlersList.put(bowlers+1,bowler);
            }

            if(runsOnThisBall==10) //condition for No Ball
            {
                freeHit=true;
                totalScore++;
                bowler.setExtras(bowler.getExtras()+1);
                bowlerController.modifyBowler(bowler);
//                System.out.println(oversTillNow+"."+ballsDoneInThisOver+" No Ball, The next ball is a FreeHit.");
                continue;
            }
            else if(runsOnThisBall==7 || runsOnThisBall==9) //Condition for Wide Ball
            {
                totalScore++;
                bowler.setExtras(bowler.getExtras()+1);
                bowlerController.modifyBowler(bowler);
//                System.out.println(oversTillNow+"."+ballsDoneInThisOver+" It's a Wide Ball.");
                continue;
            }
            else if((runsOnThisBall==5 || runsOnThisBall==8) && !freeHit){
                //wicketsInThisOver++;
                totalWickets++;
                batsmen++;
                if(striking==0){
                    batsmanOne.setBallsFaced(batsmanOne.getBallsFaced()+1);
                    batsmanController.modifyBatsman(batsmanOne);
//                    System.out.println(oversTillNow+"."+ballsDoneInThisOver+" The "+batsmanOne.getBatsmanName()+" is OUT");
                    batsmanOne=new Batsman("00","Batsman"+batsmen,teamOne,0,0,0,0);
                    batsmanController.createBatsman(batsmanOne);
                }
                else{
                    batsmanTwo.setBallsFaced(batsmanTwo.getBallsFaced()+1);
                    batsmanController.modifyBatsman(batsmanTwo);
//                    System.out.println(oversTillNow+"."+ballsDoneInThisOver+" The "+batsmanTwo.getBatsmanName()+" is OUT");
                    batsmanTwo=new Batsman("00","Batsman"+batsmen,teamOne,0,0,0,0);
                    batsmanController.createBatsman(batsmanTwo);
                }
                bowler.setWicketsTaken(bowler.getWicketsTaken()+1);
            }
            else{
                if(runsOnThisBall==5 || runsOnThisBall==8){
                    runsOnThisBall=0;
                }

                if(striking==0){
                    batsmanOne.setRunsScored(batsmanOne.getRunsScored()+runsOnThisBall);
                    batsmanOne.setBallsFaced(batsmanOne.getBallsFaced()+1);
                    if(runsOnThisBall==4){
                        batsmanOne.setFours(batsmanOne.getFours()+1);
//                        System.out.println(oversTillNow+"."+ballsDoneInThisOver+" The "+batsmanOne.getBatsmanName()+" has beaten a Marvelous Four.");
                    }
                    else if(runsOnThisBall==6){
                        batsmanOne.setSixes(batsmanOne.getSixes()+1);
//                        System.out.println(oversTillNow+"."+ballsDoneInThisOver+" The "+batsmanOne.getBatsmanName()+" has beaten Tremendous Six.");
                    }
                    else{
//                        System.out.println(oversTillNow+"."+ballsDoneInThisOver+" The "+batsmanOne.getBatsmanName()+" has scored "+runsOnThisBall+" runs.");
                    }
                }
                else{
                    batsmanTwo.setRunsScored(batsmanTwo.getRunsScored()+runsOnThisBall);
                    batsmanTwo.setBallsFaced(batsmanTwo.getBallsFaced()+1);
                    if(runsOnThisBall==4){
                        batsmanTwo.setFours(batsmanTwo.getFours()+1);
//                        System.out.println(oversTillNow+"."+ballsDoneInThisOver+" The "+batsmanTwo.getBatsmanName()+" has beaten a Marvelous Four.");
                    }
                    else if(runsOnThisBall==6){
                        batsmanTwo.setSixes(batsmanTwo.getSixes()+1);
//                        System.out.println(oversTillNow+"."+ballsDoneInThisOver+" The "+batsmanTwo.getBatsmanName()+" has beaten Tremendous Six.");
                    }
                    else{
//                        System.out.println(oversTillNow+"."+ballsDoneInThisOver+" The "+batsmanOne.getBatsmanName()+" has scored "+runsOnThisBall+" runs.");
                    }
                }
                totalScore+=runsOnThisBall;
                bowler.setRunsGiven(bowler.getRunsGiven()+runsOnThisBall);
            }
            if(runsOnThisBall==1 || runsOnThisBall==3){
                striking^=1;
            }
            freeHit=false;
            if(ballsDoneInThisOver==6){
                oversTillNow+=1;
                ballsDoneInThisOver=0;
                bowler.setOverBowled(bowler.getOverBowled()+1);
                striking^=1;
                bowlers++;
                bowlers%=players;
            }
            bowler.setLeftOverBalls(ballsDoneInThisOver);
            ballsDoneInThisOver++;
            bowlerController.modifyBowler(bowler);
            batsmanController.modifyBatsman(batsmanOne);
            batsmanController.modifyBatsman(batsmanTwo);
        }
        System.out.println(teamOne+" has scored "+totalScore+" with a loss of "+totalWickets+" players.");
        return totalScore;
    }
}
