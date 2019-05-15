package org.flaurens.tennis.domain.model;

public class GameScore {

    private final Points firstPlayerPoints;

    private final Points secondPlayerPoints;

    public GameScore(Points firstPlayerPoints, Points secondPlayerPoints){
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
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

    public boolean isFinalScore(){
        return this.firstPlayerPoints.isWinning() || this.secondPlayerPoints.isWinning();
    }
}
