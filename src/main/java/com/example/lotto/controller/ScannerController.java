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

/**
 * ScannerController dient und verwaltet alle Kommunikation mit dem Benuzer per Konsolenbefehl
 */
@Component
public class ScannerController {

    @Autowired
    private UnluckyNumberService service;


    private static final Scanner scanner = new Scanner(System.in);


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String input;
    private static Game game;

    /**
     * startGame start das Spiel und verwaltet das Spiel Verfahren.
     */
    public void startGame() {
        GameFactory gameFactory = new GameFactory();
        game = gameFactory.getGame(detectGame());
        List<Integer> unluckyList = getUnluckyNumbers();
        System.out.println("Die Unglückszahlen sind: " + Utilities.listIntToString(unluckyList));
        System.out.println("Generierte Tippreihe für das Spiel " + game.getGameName() + " sind: " + game.randomTipp(unluckyList));
        if (game.getSuperZahl() == 2) {
            System.out.println("Generierte Superzahlen für das Spiel " + game.getGameName() + " sind: " + game.randomSuperZahlTipp(unluckyList));
        }

    }


    /**
     * getUnluckyNumbers versucht die letzte Unglückzahlen zu finden, wenn die vorhanden sind.
     * Der Benutzer kann die vorhandene Unglückzahlen bearbeiten oder löschen - selectOption(lastUnlucky).
     * Wenn Unglückzahlen nicht vorhanden sind, der Bnutzer die eingeben kann- addNumbers()
     *
     * @return ein Integer List von Unglückzahlen oder ein leere List
     */
    private List<Integer> getUnluckyNumbers() {
        List<Integer> numbers = new ArrayList<>();
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
                        numbers = addNumbers();
                        if (!numbers.isEmpty()) {
                            service.addUnluckyNumber(new UnluckyNumbers(Utilities.listIntToString(numbers)));
                        }
                        return numbers;

                    } else throw new IllegalArgumentException();
                } catch (IllegalArgumentException ex) {
                    if (!input.equalsIgnoreCase("nein")) { // Wrong input
                        logger.warn("ungültiger Parameter {}", ex.getMessage());
                        System.out.println("möchten sie die Unglückszahlen feslegen? Bitte geben Sie ja oder nein ");
                    }
                }
            } while (!input.equalsIgnoreCase("nein"));


            return numbers;
        }

    }

    /**
     * selectOption fragt der Benutzer nach die Verwaltung von Unglückzahlen und ruft die relevante Methode an.
     */
    private void selectOption(UnluckyNumbers lastUnlucky) {
        String numbers = lastUnlucky.getUnluckyNumbers();
        int num = 0;
        System.out.println("Sie haben am " + lastUnlucky.getCreateDate() + " diese Ungückgligzahlen: " + numbers + " angegeben.\n");
        System.out.println("Bitte geben Sie eine Nummer ein für:\n");
        System.out.println("1- Die Ungückgligzahlen bearbeiten\n");
        System.out.println("2- Die Ungückgligzahlen löschen\n");
        System.out.println("3- Tippreihe generieren\n");

        boolean flag = true;
        do {
            try {
                num = scanner.nextInt();
                if (num > 0 && num <= 3) {
                    flag = false;
                } else {
                    System.out.println("Bitte geben sie eine Nummer zwischen 1 und 3 an");
                    scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("Bitte geben sie eine Nummer zwischen 1 und 3 an");
                scanner.nextLine();
            }
        } while (flag);
        System.out.println("---------------------------------");


        switch (num) {
            case 1:
                update(lastUnlucky);
                break;
            case 2:
                delete(lastUnlucky.getId());
                break;
            default:
                break;

        }

    }

    /**
     * addNumbers nimmt bis zu 6 Unglückzahlen vom Benutzer und speichert in Unlucky Tabelle.
     * Die Abfrage wird entweder mit der Eingabe die 6 Zahlen oder "ende" beendet.
     * Die Zahlen sollen in der vorher difinierte RangeTippZahlen und keine doppelten sein
     *
     * @return ein Integer List von Unglückzahlen oder ein leere List
     */
    private List<Integer> addNumbers() {
        List<Integer> unluckyList = new ArrayList<>();
        int unlucky;
        System.out.println("bitte geben sie 6 Unglückszahlen zwischen 1 und " + game.getRangeTippZahlen() + " ODER" + " um Tippreihe zu bekommen, schreib bitte End");
        String input = "";
        scanner.nextLine();
        while (!input.equalsIgnoreCase("end") && unluckyList.size() < 6) {

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
                    System.out.println("bitte geben Sie nur Nummer oder end");
                }
            }
        }
        Collections.sort(unluckyList);
        return unluckyList;
    }

    /**
     * delete löscht die Unglückzahlen von der Tabelle
     */
    private void delete(Long id) {
        service.deleteLastUnluckyNumbers(id);
        System.out.println(" Deine Unglückszahlen wird gelöcht");
    }

    /**
     * update aktuallisiert die Unglückzahlen in der Tabelle
     */
    private void update(UnluckyNumbers unluckyNumbers) {
        List<Integer> integerlist = addNumbers();
        if (!integerlist.isEmpty()) {
            String res = Utilities.listIntToString(integerlist);
            unluckyNumbers.setUnluckyNumbers(res);
            service.updateUnluckyNumbers(unluckyNumbers);
        }

    }

    /**
     * validate prüfft, ob die eingegebene Zahl im Bereich der Tippzahlen ist
     *
     * @param input die eingegebene Zahl
     * @param game  die Game OBJ
     * @return true, false
     */
    private static Boolean validate(int input, Game game) {
        return input <= game.getRangeTippZahlen() && input > 0;
    }

    /**
     * detectGame, Nach Eingabe des Benutzers liefert ein Lotto Type zurück.
     *
     * @return ein LottoType
     */
    protected LottoType detectGame() {
        boolean flag = true;
        System.out.print("Bitte wahlen Sie das Lottospiel:\n 1-" + LottoType.LOTTO6AUS49 + "\n 2-" + LottoType.EUROJACKPOT + "\n");
        String spiel = scanner.nextLine();
        do {
            try {
                if (spiel.isEmpty()) {
                    System.out.println("Herzlich willkommen bei " + LottoType.LOTTO6AUS49);
                    flag = false;
                    return LottoType.LOTTO6AUS49;
                } else if (LottoType.EUROJACKPOT.equalValue(spiel) || LottoType.LOTTO6AUS49.equalValue(spiel)) {
                    System.out.println("Herzlich willkommen bei " + LottoType.valueOf(spiel.toUpperCase(Locale.ROOT)));
                    flag = false;
                    return LottoType.valueOf(spiel.toUpperCase(Locale.ROOT));

                } else {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {
                System.out.println("bitte geben sie gultige Parameter\n" +
                        "mögliche Parameter sind: " + LottoType.LOTTO6AUS49 + " " + LottoType.EUROJACKPOT);
                spiel = scanner.nextLine();
            }
        } while (flag);

        System.out.println("Herzlich willkommen bei " + spiel);
        return LottoType.valueOf(spiel.toUpperCase(Locale.ROOT));
    }
}
