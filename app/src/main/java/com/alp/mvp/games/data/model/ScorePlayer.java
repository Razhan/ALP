package com.alp.mvp.games.data.model;

import java.util.ArrayList;
import java.util.List;

public class ScorePlayer {

    private String goalPlayer;
    private List<String> assistPlayers;

    public String getGoalPlayer() {
        return goalPlayer;
    }

    public void setGoalPlayer(String goalPlayer) {
        this.goalPlayer = goalPlayer;
    }

    public List<String> getAssistPlayers() {
        return assistPlayers;
    }

    public void setAssistPlayers(List<String> assistPlayers) {
        this.assistPlayers = assistPlayers;
    }

    public void removeGoalPlayer() {
        setGoalPlayer(null);
    }

    public void removeAssistPlayer(String player) {
        if (assistPlayers == null) {
            assistPlayers = new ArrayList<>();
        }

        if (assistPlayers.contains(player)) {
            assistPlayers.add(player);
        }
    }

    public void addAssistPlayer(String player) {
        if (assistPlayers == null) {
            assistPlayers = new ArrayList<>();
        }

        assistPlayers.add(player);
    }
}
