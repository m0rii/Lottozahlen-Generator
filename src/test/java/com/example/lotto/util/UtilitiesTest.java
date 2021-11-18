package com.example.lotto.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class UtilitiesTest {

  private final String test = "1,2,3,45,49,30";
  private final List<Integer> listTest = new ArrayList<>(Arrays.asList(1, 2, 3, 45, 49, 30));

  @Test
  void stringToIntListTest() {
    assertEquals(listTest, Utilities.stringToIntList(test));
    assertEquals(new ArrayList<>(), Utilities.stringToIntList(null));
    assertEquals(new ArrayList<>(), Utilities.stringToIntList(""));
    assertNotEquals(listTest, Utilities.stringToIntList("[1,2,3,45,49,30]"));
    assertNotEquals(listTest, Utilities.stringToIntList("1,,3,45,49,30"));
  }

  @Test
  void listIntToStringTest() {
    assertEquals(test, Utilities.listIntToString(listTest));
    assertEquals("", Utilities.listIntToString(new ArrayList<>()));
    assertEquals("", Utilities.listIntToString(null));
    assertNotEquals("12,3,45,74", Utilities.listIntToString(listTest));
  }

}