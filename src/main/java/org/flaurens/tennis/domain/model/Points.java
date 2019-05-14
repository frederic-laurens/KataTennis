package org.flaurens.tennis.domain.model;

public enum Points {

    SCORE0 ("SCORE15", false),
    SCORE15 ("SCORE30", false),
    SCORE30 ("SCORE40", false),
    SCORE40 ("WIN", false),
    WIN ("INVALID", true),
    INVALID ("INVALID", false);

    private final String next;

    private final boolean isWinning;

    Points(String next, boolean isWinning){
        this.next = next;
        this.isWinning = isWinning;
    }

    public Points getNextOnScoring(){
        return Points.valueOf(next);
    }

    public boolean isWinning(){
        return isWinning;
    }
}
