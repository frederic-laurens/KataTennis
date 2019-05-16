package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.scores.GameScore;

import java.util.ArrayList;


public class TennisGame extends TennisPhase {

    public TennisGame(){
        isOver = false;
        this.scoreEvolution = new ArrayList<>();
        GameScore startingScore = GameScore.initialScore();
        gameScore = startingScore;
        scoreEvolution.add(startingScore);
    }
}
