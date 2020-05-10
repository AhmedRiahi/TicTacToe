package com.tictactoe.kernel;


import com.tictactoe.kernel.logic.GameJudge;
import com.tictactoe.kernel.model.GameConfiguration;
import com.tictactoe.kernel.model.GameSession;

public class GameInitializer {

    private final static GameInitializer INSTANCE = new GameInitializer();

    private GameInitializer(){}


    public  static GameInitializer getInstance(){
        return GameInitializer.INSTANCE;
    }

    public GameJudge createGame(GameConfiguration gameConfiguration){
        GameSession gameSession = new GameSession(gameConfiguration);
       return new GameJudge(gameSession);
    }
}
