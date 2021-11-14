package com.example.lotto.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Utilities dient als Helper Klasse
 * @author  Morteza Nabhan
 */
public class Utilities {
    private static final Logger logger = LoggerFactory.getLogger(Utilities.class.getName());
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * konventiert ein String zu einem List von Integer
     * @param input eine String von mit Komma geterenden zahlen "5,12,33,45"
     * @return eine List von Integer
     * @exception NumberFormatException wenn String zu Integer nicht konventiert kann
     */
   public static List<Integer> stringToIntList(String input){
       try {
           List<String> list = Arrays.asList(input.substring(0, input.length() - 1).split(","));
           return list.stream().map(Integer::parseInt).collect(Collectors.toList());
       }catch (NumberFormatException e){
           List<Integer> emptyList = new ArrayList<>();
           logger.info("parsing error : {} ",e.getMessage());
           return  emptyList;
       }

   }

    /**
     * konventiert ein List von Integer zu einem mit Kommagetrennten String
     * @param list ein List von Integer
     * @return ein String
     */
    public static String listIntToString(List<Integer> list){
        return list.stream() .map(String::valueOf) .collect(Collectors.joining(","));
    }


}
