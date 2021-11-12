package com.example.lotto.model;

import java.util.List;

public interface tippGenerator {

     List<Integer> randomTipp(List<Integer> unluckyNums);
     List<Integer> randomSuperZahlTipp(List<Integer> unluckyNums);
}