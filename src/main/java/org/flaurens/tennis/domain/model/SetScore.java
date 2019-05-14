package org.flaurens.tennis.domain.model;

public class SetScore {

    private final Points firstPlayerPoints;

    private final Points secondPlayerPoints;

    public SetScore(Points firstPlayerPoints, Points secondPlayerPoints){
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

        SetScore setScore = (SetScore) o;

        if (firstPlayerPoints != setScore.firstPlayerPoints) return false;
        return secondPlayerPoints == setScore.secondPlayerPoints;
    }

    @Override
    public int hashCode() {
        int result = firstPlayerPoints != null ? firstPlayerPoints.hashCode() : 0;
        result = 31 * result + (secondPlayerPoints != null ? secondPlayerPoints.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SetScore{" +
                "firstPlayerPoints=" + firstPlayerPoints +
                ", secondPlayerPoints=" + secondPlayerPoints +
                '}';
    }

    public boolean isFinalScore(){
        return this.firstPlayerPoints.isWinning() || this.secondPlayerPoints.isWinning();
    }
}
