package org.flaurens.tennis.domain.model.scores;

public enum Points {

    SCORE0 ("SCORE15", "SCORE0", false),
    SCORE15 ("SCORE30", "SCORE15", false),
    SCORE30 ("SCORE40", "SCORE30", false),
    SCORE40 ("WIN", "SCORE40", false),
    WIN ("WIN", "WIN", true),
    DEUCE("ADV","SCORE40", false),
    ADV("WIN","DEUCE", false);

    private final String nextOnWonPoint;

    private final String nextOnLostPoint;

    private final boolean isWinning;

    Points(String nextOnWonPoint, String nextOnLostPoint, boolean isWinning){
        this.nextOnWonPoint = nextOnWonPoint;
        this.nextOnLostPoint = nextOnLostPoint;
        this.isWinning = isWinning;
    }

    public Points getNextOnWonPoint(){
        return Points.valueOf(nextOnWonPoint);
    }

    public Points getNextOnLostPoint(){
        return Points.valueOf(nextOnLostPoint);
    }

    public boolean isWinning(){
        return isWinning;
    }
}
