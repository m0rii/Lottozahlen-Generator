package com.example.lotto.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities dient als Helper Klasse
 *
 * @author Morteza Nabhan
 */
public class Utilities {
  private static final Logger logger = LoggerFactory.getLogger(Utilities.class.getName());
  private static final Scanner scanner = new Scanner(System.in);

  /**
   * konventiert ein String zu einem List von Integer
   *
   * @param input eine String von mit Komma geterenden zahlen "5,12,33,45"
   * @return eine List von Integer
   * @throws NumberFormatException wenn String zu Integer nicht konventiert kann
   */
  public static List<Integer> stringToIntList(String input) {

    try {
      List<String> list = Arrays.asList(input.split(","));
      return list.stream().map(Integer::parseInt).collect(Collectors.toList());
    } catch (NumberFormatException | NullPointerException e) {
      logger.error("parsing error : {} ", e.getClass() + " : " + e.getMessage());
      return new ArrayList<>();
    }
  }

  /**
   * konventiert ein List von Integer zu einem mit Kommagetrennten String
   *
   * @param list ein List von Integer
   * @return ein String
   */
  public static String listIntToString(List<Integer> list) {
    try {
      return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    } catch (NullPointerException e) {
      logger.error("list in listIntToString is null : {} ", e.getClass() + " : " + e.getMessage());
      return "";
    }
  }
}
