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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TieBreakScore that = (TieBreakScore) o;

        if (firstPlayerPoints != that.firstPlayerPoints) return false;
        return secondPlayerPoints == that.secondPlayerPoints;
    }

    @Override
    public int hashCode() {
        int result = firstPlayerPoints;
        result = 31 * result + secondPlayerPoints;
        return result;
    }

    @Override
    public String toString() {
        return "TieBreakScore{" +
                "firstPlayerPoints=" + firstPlayerPoints +
                ", secondPlayerPoints=" + secondPlayerPoints +
                '}';
    }
}
