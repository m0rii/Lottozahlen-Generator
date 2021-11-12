package com.example.lotto.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utilities {
    private static final Logger logger = LoggerFactory.getLogger(Utilities.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
   public static List<Integer> stringToIntList(String s){
       try {
           return Stream.of(s.split(","))
                   .map(Integer::parseInt)
                   .collect(Collectors.toList());
       }catch (NumberFormatException e){
           List<Integer> emptyList = new ArrayList<>();
           logger.info("parsing error : {} ",e.getMessage());
           return  emptyList;
       }

   }

    public static String listIntToString(List<Integer> list){
        return Arrays.toString(list.toArray());
    }

  /*  public void inputInt(List<Integer> expecp, int filter, int repeat){
       int tmp=-1;
       String input;

       List<Integer> inputList;
          do {
            try {
                input = scanner.nextLine();
                tmp = Integer.parseInt(input); // Cast the number, if it does not succeed catch the exception.
                if ((!inputList.contains(unlucky))) {
                    inputList.add(unlucky);
                } else {
                    System.out.println("your number ist out of range or dublicate, choose number between 1 to  " + game.getRangeTippZahlen());
                }

            } catch (NumberFormatException e) {
                if (!input.equalsIgnoreCase("end")) { // Wrong input
                    System.out.println("Wrong input, input another number or end");
                }
            }
        } while (!input.equalsIgnoreCase("end") && unluckyList.size() < 6);


    }
    private static int validate(int filter) {
        return 6;
    }
    */


}
