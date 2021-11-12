package com.example.lotto.model;

import com.example.lotto.util.LottoType;

public class GameFactory {

    public Game getGame(LottoType lottoType){
        Game game = new Game();
        if(lottoType == null){
            return null;
        }
        switch (lottoType) {
            case LOTTO6AUS49:
                game.setRangeTippZahlen(49);
                game.setSuperZahl(1);
                game.setTippZahlen(6);
                game.setRangeSuperZahl(10);
                game.setGameName(lottoType.name());
                break;
            case EUROJACKPOT:
                game.setRangeTippZahlen(50);
                game.setSuperZahl(2);
                game.setTippZahlen(5);
                game.setRangeSuperZahl(10);
                game.setGameName(lottoType.name());
                break;
        }
        return game;
    }
}
