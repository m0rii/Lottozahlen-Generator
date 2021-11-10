package com.example.lotto.controller;

import com.example.lotto.model.Game;
import com.example.lotto.model.GameFactory;
import com.example.lotto.service.UnluckyNumberService;
import com.example.lotto.util.LottoType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ScannerCotroller  {

    @Autowired
    private UnluckyNumberService service;


    private static Scanner scanner = new Scanner(System.in);


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String input;
    private static Game game;


    public void startGame() {
      service.deleteUnluckyNumbers();
       GameFactory gameFactory = new GameFactory();
        game = gameFactory.getGame(detectGame());
        List<Integer> unluckyList = getUnluckyNumbers();
        if (! unluckyList.isEmpty()){

         logger.info("Inserting -> {}",
                  service.getAllUnluckyNumbers());
             //     repository.save(new UnluckyNumbers(Arrays.toString(unluckyList.toArray()), new Date())));
        }

    }


    private static List<Integer> getUnluckyNumbers() {
        int unlucky;

        System.out.println("möchten sie ungluck feslegen? Ja / Nein");
        input = scanner.nextLine();
        if(input.toLowerCase(Locale.ROOT).equals("ja")) {
            return addNumbers();
        }
        else return new ArrayList<>();
    }

    private static List<Integer> addNumbers() {
        List<Integer> unluckyList = new ArrayList<>();
        int unlucky;
        System.out.println("bitte geben sie 6 ungluck zahlen");

        do {

            try {
                input=scanner.nextLine();
                unlucky= Integer.parseInt(input); // Cast the number, if it does not succeed catch the exception.
                if(validate(unlucky ,game) && (!unluckyList.contains(unlucky))){
                    unluckyList.add(unlucky);

                }else{
                    System.out.println("your number ist out of range , choose number between 1 to  " + game.getRangeTippZahlen());
                }

            } catch(NumberFormatException e) {
                if(!input.equalsIgnoreCase("end")) { // Wrong input
                    System.out.println("Wrong input, input another number or end");
                }
            }
        } while (!input.equalsIgnoreCase("end") && unluckyList.size()<6 );


        System.out.println("deine ungluckliche zahlen sind" + Arrays.toString(unluckyList.toArray()));
        System.out.println(game.randomTipp());
        System.out.println(game.randomSuperZahlTipp());
        scanner.close();
        return unluckyList;
    }



    private static Boolean validate(int input , Game game){
        return input <= game.getRangeTippZahlen() && input > 0;
    }


    private static LottoType detectGame() {
        System.out.print("Bitte wahlen Sie der Spiel:\n 1-" + LottoType.LOTTO6AUS49 + "\n 2-" + LottoType.EUROJACKPOT + "\n");
        String spiel = scanner.nextLine();
        System.out.println("Sie haben der " + spiel + " gewahlt");
        return LottoType.valueOf(spiel.toUpperCase(Locale.ROOT));
    }

    private static String readDb(){

        return null;
    }
}
