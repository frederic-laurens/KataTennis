package org.flaurens.tennis.domain.model;

import org.flaurens.tennis.domain.model.scores.TieBreakScore;

import java.util.ArrayList;

public class TieBreak extends TennisPhase {

    public TieBreak(){
        isOver = false;
        this.scoreEvolution = new ArrayList<>();
        TieBreakScore startingScore = TieBreakScore.initialScore();
        gameScore = startingScore;
        scoreEvolution.add(startingScore);
    }
}
