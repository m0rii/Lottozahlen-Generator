package com.example.lotto.controller;

import com.example.lotto.model.Game;
import com.example.lotto.model.GameFactory;
import com.example.lotto.util.LottoType;

import java.util.Locale;
import java.util.Scanner;

public class ScannerCotroller {

    public static void startGame() {
        GameFactory gameFactory = new GameFactory();
       Game game = gameFactory.getGame(getGameType());
        System.out.println(game.randomTipp());
        System.out.println(game.randomSuperZahlTipp());

    }


    private static LottoType getGameType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bitte wahlen Sie der Spiel:\n 1-" + LottoType.LOTTO6AUS49 + "\n 2-" + LottoType.EUROJACKPOT + "\n");
        String spiel = scanner.nextLine();
        System.out.println("Sie haben der " + spiel + " gewahlt");
        return LottoType.valueOf(spiel.toUpperCase(Locale.ROOT));
    }

}
