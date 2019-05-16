package org.flaurens.tennis.domain.model.scoringevents;

public class ScoringEvent {

    private final boolean firstPlayerWins;

    public ScoringEvent(boolean firstPlayerWins){
        this.firstPlayerWins = firstPlayerWins;
    }

    public boolean isFirstPlayerWins() {
        return firstPlayerWins;
    }

    public static ScoringEvent firstPlayerWins(){
        return new ScoringEvent(true);
    }

    public static ScoringEvent secondPlayerWins(){
        return new ScoringEvent(false);
    }
}
