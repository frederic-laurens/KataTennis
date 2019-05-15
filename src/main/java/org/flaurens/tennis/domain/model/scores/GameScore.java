package org.flaurens.tennis.domain.model.scores;

public class GameScore implements Score {

    private final Points firstPlayerPoints;

    private final Points secondPlayerPoints;

    public GameScore(Points firstPlayerPoints, Points secondPlayerPoints){
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
    }

    public static GameScore initialScore(){
        return new GameScore(Points.SCORE0, Points.SCORE0);
    }

    public Points getFirstPlayerPoints() {
        return firstPlayerPoints;
    }

    public Points getSecondPlayerPoints() {
        return secondPlayerPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameScore gameScore = (GameScore) o;

        if (firstPlayerPoints != gameScore.firstPlayerPoints) return false;
        return secondPlayerPoints == gameScore.secondPlayerPoints;
    }

    @Override
    public int hashCode() {
        int result = firstPlayerPoints != null ? firstPlayerPoints.hashCode() : 0;
        result = 31 * result + (secondPlayerPoints != null ? secondPlayerPoints.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameScore{" +
                "firstPlayerPoints=" + firstPlayerPoints +
                ", secondPlayerPoints=" + secondPlayerPoints +
                '}';
    }

    public boolean isWinningScore(){
        return this.firstPlayerPoints.isWinning() || this.secondPlayerPoints.isWinning();
    }

    @Override
    public Score getCopy() {
        return new GameScore(this.firstPlayerPoints,this.secondPlayerPoints);
    }
}
