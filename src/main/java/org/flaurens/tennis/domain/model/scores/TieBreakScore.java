package org.flaurens.tennis.domain.model.scores;

public class TieBreakScore implements Score {

    private int firstPlayerPoints;

    private int secondPlayerPoints;

    public TieBreakScore(int firstPlayerPoints, int secondPlayerPoints){
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
    }

    public Integer getFirstPlayerPoints() {
        return firstPlayerPoints;
    }

    public Integer getSecondPlayerPoints() {
        return secondPlayerPoints;
    }

    public static TieBreakScore initialScore(){
        return new TieBreakScore(0,0);
    }

    private boolean oneOfThePlayerHasReached7(){
        return this.firstPlayerPoints > 6 || this.secondPlayerPoints > 6;
    }

    private boolean thereIsADifferenceOfAtLeastTwoPoints(){
        return Math.abs(this.firstPlayerPoints - this.secondPlayerPoints) > 1;
    }

    public boolean isWinningScore(){
        return this.oneOfThePlayerHasReached7() && thereIsADifferenceOfAtLeastTwoPoints();
    }

    @Override
    public Score getCopy() {
        return new TieBreakScore(this.firstPlayerPoints,this.secondPlayerPoints);
    }
}
