package com.tictactoe.kernel.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Team {

    private int id;
    private String name;
    private List<Player> players = new ArrayList<>();

    public Team(String name, int id){
        this.name = name;
        this.id = id;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public boolean hasPlayer(Player player){
        return this.players.stream().anyMatch(currentPlayer -> currentPlayer.equals(player));
    }
}
