package com.tictactoe.kernel.logic;

import com.tictactoe.kernel.model.GameBoard;
import com.tictactoe.kernel.model.Position;

public class WinnerCheckingHelper {

    public static int getSamePositions(GameBoard gameBoard, Position initialPosition, int x , int y, int teamId){
        int samePositionsCount = 0;
        Position nextPosition = new Position(initialPosition.getX() - x, initialPosition.getY() -y);
        do{
            if(gameBoard.getValue(nextPosition) == teamId){
                samePositionsCount++;
            }
            nextPosition = new Position(nextPosition.getX() - x, nextPosition.getY() -y);
        }while(gameBoard.getValue(nextPosition) == teamId);
        return samePositionsCount;
    }
}
