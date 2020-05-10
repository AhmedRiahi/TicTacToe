package com.tictactoe.kernel.logic;

import com.tictactoe.kernel.exception.GameAlreadyFinishedException;
import com.tictactoe.kernel.exception.InvalidBoardPositionException;
import com.tictactoe.kernel.exception.InvalidPlayerException;
import com.tictactoe.kernel.exception.PlayerNotFoundException;
import com.tictactoe.kernel.model.GameSession;
import com.tictactoe.kernel.model.Player;
import com.tictactoe.kernel.model.Position;
import com.tictactoe.kernel.model.Team;

import java.util.ArrayList;
import java.util.List;

public class GameJudge {

    private GameSession gameSession;
    private int currentPlayerIndex;
    private List<Player> playersOrder = new ArrayList<>();
    private Player winner;

    public GameJudge(GameSession gameSession){
        this.gameSession = gameSession;
        this.currentPlayerIndex = 0;
    }

    public Player getNextPlayer(){
        if(this.currentPlayerIndex == this.playersOrder.size() -1){
            return this.playersOrder.get(0);
        }
        return this.playersOrder.get(this.currentPlayerIndex+1);
    }

    public void play(Player player, Position position){
        // check that there is no winner
        if(this.winner != null){
            throw new GameAlreadyFinishedException();
        }

        // check that player is the right player order
        if(!this.getNextPlayer().equals(player)){
            throw new InvalidPlayerException();
        }

        // check that board position is empty
        if(!this.gameSession.getGameBoard().isEmpty(position)){
            throw new InvalidBoardPositionException();
        }

        Team playerTeam = this.gameSession.getPlayerTeam(player).orElseThrow(PlayerNotFoundException::new);

        this.gameSession.getGameBoard().fillPosition(position,playerTeam.getId());

        if(this.checkWinner(position, playerTeam.getId())){
            this.winner = player;
        }
        this.currentPlayerIndex++;
    }

    public boolean checkWinner(Position lastPosition, int teamId){
        int rightCount = WinnerCheckingHelper.getSamePositions(this.gameSession.getGameBoard(),lastPosition,0,1,teamId);
        int leftCount = WinnerCheckingHelper.getSamePositions(this.gameSession.getGameBoard(),lastPosition,0,-1,teamId);

        if(rightCount + leftCount + 1 == this.gameSession.getGameConfiguration().getWinSequenceLength()){
            return true;
        }

        int upCount = WinnerCheckingHelper.getSamePositions(this.gameSession.getGameBoard(),lastPosition,-1,0,teamId);
        int downCount = WinnerCheckingHelper.getSamePositions(this.gameSession.getGameBoard(),lastPosition,1,0,teamId);

        if(upCount + downCount + 1 == this.gameSession.getGameConfiguration().getWinSequenceLength()){
            return true;
        }

        int upLeftCount = WinnerCheckingHelper.getSamePositions(this.gameSession.getGameBoard(),lastPosition,-1,-1,teamId);
        int downRightCount = WinnerCheckingHelper.getSamePositions(this.gameSession.getGameBoard(),lastPosition,1,1,teamId);

        if(upLeftCount + downRightCount + 1 == this.gameSession.getGameConfiguration().getWinSequenceLength()){
            return true;
        }

        int upRightCount = WinnerCheckingHelper.getSamePositions(this.gameSession.getGameBoard(),lastPosition,-1,1,teamId);
        int downLeftCount = WinnerCheckingHelper.getSamePositions(this.gameSession.getGameBoard(),lastPosition,1,-1,teamId);

        if(upRightCount + downLeftCount + 1 == this.gameSession.getGameConfiguration().getWinSequenceLength()){
            return true;
        }

        return false;
    }
}
