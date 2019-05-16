package org.flaurens.tennis.domain.model.scores;

public class SetScore implements Score {

    private final int firstPlayerPoints;

    private final int secondPlayerPoints;

    public SetScore(int firstPlayerPoints, int secondPlayerPoints){
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
    }

    public static SetScore initialScore(){
        return new SetScore(0,0);
    }

    public Integer getFirstPlayerPoints() {
        return firstPlayerPoints;
    }

    public Integer getSecondPlayerPoints(){
        return secondPlayerPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetScore setScore = (SetScore) o;

        if (firstPlayerPoints != setScore.firstPlayerPoints) return false;
        return secondPlayerPoints == setScore.secondPlayerPoints;
    }

    @Override
    public int hashCode() {
        int result = firstPlayerPoints;
        result = 31 * result + secondPlayerPoints;
        return result;
    }

    @Override
    public String toString() {
        return "SetScore{" +
                "firstPlayerPoints=" + firstPlayerPoints +
                ", secondPlayerPoints=" + secondPlayerPoints +
                '}';
    }

    private boolean oneOfThePlayerHasReached7(){
        return this.firstPlayerPoints ==7 || this.secondPlayerPoints ==7;
    }

    private boolean oneOfThePlayerHasReached6WithA2PointDifference(){
        return (this.firstPlayerPoints == 6 && this.secondPlayerPoints < 5)
                || (this.secondPlayerPoints == 6 && this.firstPlayerPoints < 5);
    }

    public boolean isWinningScore(){
        return oneOfThePlayerHasReached7() || oneOfThePlayerHasReached6WithA2PointDifference();
    }

    public boolean isTieBreakScore(){
        return this.firstPlayerPoints == 6 && this.secondPlayerPoints == 6;
    }

    @Override
    public Score getCopy() {
        return new SetScore(this.firstPlayerPoints,this.secondPlayerPoints);
    }
}
