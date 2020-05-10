package com.tictactoe.kernel.model;

import com.tictactoe.kernel.exception.GameBoardOutOfBoundException;

public class GameBoard {

    private int[][] matrix;
    private int size;

    public GameBoard(int size){
        this.size = size;
        this.matrix = new int[size][size];
    }


    public int getValue(Position position){
        if(!this.isPositionBounded(position)) return 0;
        return  this.matrix[position.getX()][position.getY()];
    }

    public boolean isEmpty(Position position){
        if(!this.isPositionBounded(position)) return false;
        return this.getValue(position) == 0;
    }

    public void fillPosition(Position position, int value){
        if(!this.isPositionBounded(position)) throw new GameBoardOutOfBoundException();
        this.matrix[position.getX()][position.getY()] = value;
    }

    private boolean isPositionBounded(Position position){
        return position.getX() > 0 && position.getX() < this.size &&
                position.getY() > 0 && position.getY() < this.size;
    }
}
