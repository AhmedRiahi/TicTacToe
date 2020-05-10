package com.tictactoe.kernel.model;

import com.tictactoe.kernel.logic.GameJudge;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@Data
public class GameSession {

    private UUID id;
    private List<Team> teams;
    private GameBoard gameBoard;
    private GameConfiguration gameConfiguration;

    public GameSession(GameConfiguration gameConfiguration){
        this.gameConfiguration = gameConfiguration;
        IntStream.of(this.gameConfiguration.getTeamsNumber()).mapToObj(i -> new Team("Team "+i, i+1)).forEach(teams::add);
        for(Team team : this.teams){
            IntStream.of(this.gameConfiguration.getTeamPlayersNumber()).mapToObj(i -> new Player("Player "+i)).forEach(team::addPlayer);
        }
        this.gameBoard = new GameBoard(this.gameConfiguration.getBoardSize());
    }

    public Optional<Team> getPlayerTeam(Player player){
        return this.teams.stream().filter(team -> team.hasPlayer(player)).findAny();
    }

}
