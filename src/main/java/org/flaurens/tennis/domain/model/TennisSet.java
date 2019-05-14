package org.flaurens.tennis.domain.model;

import java.util.ArrayList;
import java.util.List;

public class TennisSet {

    private SetScore setScore;

    private final List<SetScore> scoreEvolution;

    private boolean isSetOver;

    public TennisSet(){
        this.scoreEvolution = new ArrayList<>();
        SetScore startingScore = new SetScore(Points.SCORE0, Points.SCORE0);
        isSetOver = false;
        scoreEvolution.add(startingScore);
        setScore = startingScore;
    }

    public boolean updateSetScore(ScoringEvent scoringEvent) throws SetIsAlreadyWonException{
        if(!isSetOver) {
            SetScore nextScore = scoringEvent.update(setScore);
            setScore = nextScore;
            scoreEvolution.add(nextScore);
            if (nextScore.isFinalScore()) {
                this.isSetOver = true;
            }
            return isSetOver;
        }
        throw new SetIsAlreadyWonException("The final score is : "+setScore);
    }

    public List<SetScore> getScoreEvolution(){
       return this.scoreEvolution;
    }

    public SetScore getCurrentScore() {
        return setScore;
    }
}
