package com.cricketgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bowler")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bowler{
    @Id
    String bowlerId;
    String bowlerName;
    String bowlerCountry;
    int runsGiven;
    int overBowled;
    int leftOverBalls;
    int wicketsTaken;
    int extras;

}
