package com.alp.mvp.games.data.model;

public class Score {

    private ScorePlayer player;
    private String period;
    private String time;
    private String comment;

    public ScorePlayer getPlayer() {
        return player;
    }

    public void setPlayer(ScorePlayer player) {
        this.player = player;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
