package org.flaurens.tennis.domain.model.scores;

public class GlobalScore {

    private final Score gameScore;
    private final Score setScore;
    private final Score tieBreakScore;

    public GlobalScore(Score gameScore, Score setScore) {
        this.gameScore = gameScore;
        this.setScore = setScore;
        this.tieBreakScore = null;
    }

    public GlobalScore(Score gameScore, Score setScore, Score tieBreakScore){
        this.gameScore = gameScore;
        this.setScore = setScore;
        this.tieBreakScore = tieBreakScore;
    }

    public Score getSetScore() {
        return setScore;
    }

    @Override
    public String toString() {
        return "GlobalScore{" +
                "gameScore=" + gameScore +
                ", setScore=" + setScore +
                ", tieBreakScore=" + tieBreakScore +
                '}';
    }
}
