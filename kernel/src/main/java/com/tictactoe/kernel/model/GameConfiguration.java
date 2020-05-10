package com.tictactoe.kernel.model;

import lombok.Data;

@Data
public class GameConfiguration {

    private int teamsNumber;
    private int teamPlayersNumber;
    private int boardSize;
    private int winSequenceLength;
}
