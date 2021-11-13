package com.example.lotto.controller;

import com.example.lotto.model.Game;
import com.example.lotto.model.GameFactory;
import com.example.lotto.model.UnluckyNumbers;
import com.example.lotto.service.UnluckyNumberService;
import com.example.lotto.util.LottoType;
import com.example.lotto.util.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ScannerCotroller {

    @Autowired
    private UnluckyNumberService service;


    private static final Scanner scanner = new Scanner(System.in);


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String input;
    private static Game game;


    public void startGame() {
        GameFactory gameFactory = new GameFactory();
        game = gameFactory.getGame(detectGame());
        List<Integer> unluckyList = getUnluckyNumbers();
        System.out.println("Generierte Tippreihe für das Spiel " + game.getGameName()+ " sind: " + game.randomTipp(unluckyList) );
        if(game.getSuperZahl() == 2){
            System.out.println("Generierte Superzahlen für das Spiel " + game.getGameName()+ " sind: " + game.randomSuperZahlTipp(unluckyList));
        }

    }


    private List<Integer> getUnluckyNumbers() {

        UnluckyNumbers lastUnlucky = service.getLastUnluckyNumbers();
        if (lastUnlucky != null) {
            selectOption(lastUnlucky);
            String s = lastUnlucky.getUnluckyNumbers();
            return Utilities.stringToIntList(s);
        } else {
            System.out.println("Sie haben keine Unglückszahlen ");
            System.out.println("möchten sie die Unglückszahlen feslegen? Ja / Nein");


            do {
                try {
                    input = scanner.nextLine();
                    if (input.toLowerCase(Locale.ROOT).equals("ja")) {
                        return addNumbers();
                    } else throw new InputMismatchException();
                } catch (InputMismatchException ex) {
                    if (!input.equalsIgnoreCase("nein")) { // Wrong input
                        logger.warn("ungültiger Parameter {}" , ex.getMessage());

                        System.out.println("möchten sie die Unglückszahlen feslegen? Bitte geben Sie ja oder nein ");
                    }
                }
            } while (!input.equalsIgnoreCase("nein"));



            return new ArrayList<Integer>();
        }

    }

    private void selectOption(UnluckyNumbers lastUnlucky) {
        String numbers = lastUnlucky.getUnluckyNumbers();
        int num = 0;
        System.out.println("Sie haben am " + lastUnlucky.getCreateDate() + " diese Ungückgligzahlen: " + numbers + " angegeben.\n");
        System.out.println("Bitte geben Sie eine Nummer ein für:\n");
        System.out.println("1- Die Ungückgligzahlen bearbeiten\n");
        System.out.println("2- Die Ungückgligzahlen löschen\n");
        System.out.println("3- Tippreihe generieren\n");

        boolean flag=true;
        do {
            try {
                num = scanner.nextInt();
                if(num>0 && num<=3){
                    flag=false;
                }
                else{
                    System.out.println("Bitte geben sie eine Nummer zwischen 1 und 3 an");
                    scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("Bitte geben sie eine Nummer zwischen 1 und 3 an");
                scanner.nextLine();
            }
        } while (flag);
        System.out.println("---------------------------------");


        switch (num){
            case 1 : update(lastUnlucky);
            break;
            case 2: delete(lastUnlucky.getId());
            break;
            default: break;

        }

    }

    private List<Integer> addNumbers() {
        List<Integer> unluckyList = new ArrayList<>();
        int unlucky;
        System.out.println("bitte geben sie 6 Unglückszahlen zwischen 1 und " + game.getRangeTippZahlen() );

        do {

            try {
                input = scanner.nextLine();
                unlucky = Integer.parseInt(input); // Cast the number, if it does not succeed catch the exception.
                if (validate(unlucky, game) && (!unluckyList.contains(unlucky))) {
                    unluckyList.add(unlucky);
                } else {
                    System.out.println("deine Nummer ist falsch oder doppelt, bitte geben Sie ein Nummer zwischen 1 und" + game.getRangeTippZahlen());
                }

            } catch (NumberFormatException e) {
                if (!input.equalsIgnoreCase("end")) { // Wrong input
                    System.out.println("Wrong input, input another number or end");
                }
            }
        } while (!input.equalsIgnoreCase("end") && unluckyList.size() < 6);

        System.out.println("Die Unglückszahlen sind" +Utilities.listIntToString(unluckyList));
        scanner.close();
        Collections.sort(unluckyList);
        service.addUnluckyNumber(new UnluckyNumbers(Utilities.listIntToString(unluckyList)));
        return unluckyList;
    }

    private void delete(Long id) {
        service.deleteLastUnluckyNumbers(id);
        System.out.println(" Deine Unglückszahlen wird gelöcht");
    }

    private void update(UnluckyNumbers unluckyNumbers ) {
        List<Integer> integerlist = addNumbers();
        String res = integerlist.stream() .map(String::valueOf) .collect(Collectors.joining(","));
        unluckyNumbers.setUnluckyNumbers(res);
        service.updateUnluckyNumbers(unluckyNumbers);
    }

    private static Boolean validate(int input, Game game) {
        return input <= game.getRangeTippZahlen() && input > 0;
    }


    protected static LottoType detectGame() {
        boolean flag = true;
        System.out.print("Bitte wahlen Sie das Lottospiel:\n 1-" + LottoType.LOTTO6AUS49 + "\n 2-" + LottoType.EUROJACKPOT + "\n");
        String spiel = scanner.nextLine();
        do {
            try {
                if (spiel.isEmpty()) {
                    System.out.println("Herzlich willkommen bei " + LottoType.LOTTO6AUS49);
                    flag = false;
                    return LottoType.LOTTO6AUS49;
                }else if(LottoType.EUROJACKPOT.equalValue(spiel) || LottoType.LOTTO6AUS49.equalValue(spiel)) {
                    System.out.println("Herzlich willkommen bei " + LottoType.valueOf(spiel.toUpperCase(Locale.ROOT)));
                    flag = false;
                    return LottoType.valueOf(spiel.toUpperCase(Locale.ROOT));

                }else {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {
                System.out.println("bitte geben sie gultige Parameter\n" +
                        "mögliche Parameter sind: " + LottoType.LOTTO6AUS49 + " "+LottoType.EUROJACKPOT);
              spiel=  scanner.nextLine();
            }
        } while (flag);

        System.out.println("Herzlich willkommen bei " + spiel);
        return LottoType.valueOf(spiel.toUpperCase(Locale.ROOT));
    }
}
