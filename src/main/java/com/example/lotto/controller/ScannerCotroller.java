package com.example.lotto.controller;

import com.example.lotto.model.Game;
import com.example.lotto.model.GameFactory;
import com.example.lotto.model.UnluckyNumbers;
import com.example.lotto.service.UnluckyNumberService;
import com.example.lotto.util.LottoType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ScannerCotroller {

    @Autowired
    private UnluckyNumberService service;


    private static final Scanner scanner = new Scanner(System.in);


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String input;
    private static Game game;


    public void startGame() {
        //  service.deleteAllUnluckyNumbers();
        GameFactory gameFactory = new GameFactory();
        game = gameFactory.getGame(detectGame());
        List<Integer> unluckyList = getUnluckyNumbers();
        System.out.println("unlucky list : " + unluckyList);

        System.out.println(game.randomTipp(unluckyList));

       // logger.info("Inserting -> {}",
         //       service.getAllUnluckyNumbers());
        //     repository.save(new UnluckyNumbers(Arrays.toString(unluckyList.toArray()), new Date())));
        scanner.close();
    }


    private List<Integer> getUnluckyNumbers() {
        UnluckyNumbers lastUnlucky = service.getLastUnluckyNumbers();
        if (lastUnlucky == null) {
            System.out.println("Sie haben keine Unglückszahlen ");
            System.out.println("möchten sie die Unglückszahlen feslegen? Ja / Nein");
            input = scanner.nextLine();
            if (input.toLowerCase(Locale.ROOT).equals("ja")) {
                return addNumbers();
            } else return new ArrayList<>();
        } else
            selectOption(lastUnlucky);




            return Stream.of(lastUnlucky.getUnluckyNumbers().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
    }

    private List<Integer> selectOption(UnluckyNumbers lastUnlucky) {
        String numbers = lastUnlucky.getUnluckyNumbers();
        int num = 0;
        System.out.println("Sie haben am " + lastUnlucky.getCreateDate() + "diese Ungückgligzahlen: " + numbers + "angegeben.\n");
        System.out.println("Möchten Sie Ihre Unglückzahlen bearbeiten? Bitte geben Sie eine Nummer ein für:\n");
        System.out.println("1- Die Glückzahlen bearbeiten\n");
        System.out.println("2- Die Glückzahlen löschen\n");
        System.out.println("3- Tippreihe generieren\n");

        boolean flag=true;
        do {
            try {
                num = scanner.nextInt();
                if(num>0 && num<=3){
                    flag=false;
                }else
                  System.out.println("Bitte geben sie eine Numer im Bereich 1-3 an");

            } catch (Exception e) {
                System.out.println("Bitte geben sie eine Numer");
                scanner.nextLine();
            }
        } while (flag);
        System.out.println("---------------------------------");

     List<Integer> listInt =  Stream.of(numbers.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        switch (num){
            case 1 : update(lastUnlucky);
            break;
            case 2: delete(lastUnlucky.getId());
            break;
            default: break;

        }


  return listInt;
    }

    private List<Integer> addNumbers() {
        List<Integer> unluckyList = new ArrayList<>();
        int unlucky;
        System.out.println("bitte geben sie 6 ungluck zahlen");

        do {

            try {
                input = scanner.nextLine();
                unlucky = Integer.parseInt(input); // Cast the number, if it does not succeed catch the exception.
                if (validate(unlucky, game) && (!unluckyList.contains(unlucky))) {
                    unluckyList.add(unlucky);

                } else {
                    System.out.println("your number ist out of range or dublicate, choose number between 1 to  " + game.getRangeTippZahlen());
                }

            } catch (NumberFormatException e) {
                if (!input.equalsIgnoreCase("end")) { // Wrong input
                    System.out.println("Wrong input, input another number or end");
                }
            }
        } while (!input.equalsIgnoreCase("end") && unluckyList.size() < 6);


     /*   int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                createTipp();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
                break;
        }*/


        System.out.println("deine ungluckliche zahlen sind" + Arrays.toString(unluckyList.toArray()));
        System.out.println(game.randomSuperZahlTipp());
        scanner.close();

        service.addUnluckyNumber(new UnluckyNumbers(Arrays.toString(unluckyList.toArray())));
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
        System.out.println("edit");

    }

    private static Boolean validate(int input, Game game) {
        return input <= game.getRangeTippZahlen() && input > 0;
    }


    private static LottoType detectGame() {
        System.out.print("Bitte wahlen Sie der Spiel:\n 1-" + LottoType.LOTTO6AUS49 + "\n 2-" + LottoType.EUROJACKPOT + "\n");
        String spiel = scanner.nextLine();
        if(spiel.isEmpty()){
            System.out.println("Sie haben der " + LottoType.LOTTO6AUS49 + " gewahlt");
            return LottoType.LOTTO6AUS49;
        }
        System.out.println("Sie haben der " + spiel + " gewahlt");
        return LottoType.valueOf(spiel.toUpperCase(Locale.ROOT));
    }

    private static String readDb() {

        return null;
    }
}
