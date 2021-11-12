package com.example.lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements tippGenerator {
    private int rangeTippZahlen;
    private int tippZahlen;
    private int superZahl;
    private int rangeSuperZahl;
    private String gameName;


    public int getRangeTippZahlen() {
        return rangeTippZahlen;
    }

    public void setRangeTippZahlen(int rangeTippZahlen) {
        this.rangeTippZahlen = rangeTippZahlen;
    }

    public int getSuperZahl() {
        return superZahl;
    }

    public void setSuperZahl(int superZahl) {
        this.superZahl = superZahl;
    }

    public int getRangeSuperZahl() {
        return rangeSuperZahl;
    }

    public int getTippZahlen() {
        return tippZahlen;
    }

    public void setRangeSuperZahl(int rangeSuperZahl) {
        this.rangeSuperZahl = rangeSuperZahl;
    }

    public void setTippZahlen(int tippZahlen) {
        this.tippZahlen = tippZahlen;
    }
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public List<Integer> randomTipp(List<Integer> unluckyNums) {
        List<Integer> tipps = new ArrayList<>();
        do {
            int tmp = (int) (Math.random() * this.getRangeTippZahlen() + 1);
            if (!tipps.contains(tmp) && !unluckyNums.contains(tmp)) {
                tipps.add(tmp);
            }
        } while (tipps.size() < this.getTippZahlen());

        Collections.sort(tipps);
        return tipps;
    }

    @Override
    public List<Integer> randomSuperZahlTipp(List<Integer> unluckyNums) {
        List<Integer> superZahlTipp = new ArrayList<>();
        do {
            int tmp = (int) (Math.random() * this.getRangeSuperZahl() + 1);
            if (!superZahlTipp.contains(tmp) && !unluckyNums.contains(tmp) ){
                superZahlTipp.add(tmp);
            }
        } while (superZahlTipp.size() < this.getSuperZahl());

        Collections.sort(superZahlTipp);
        return superZahlTipp;
    }

    @Override
    public String toString() {
        return "Game{" +
                "rangeTippZahlen=" + rangeTippZahlen +
                ", tippZahlen=" + tippZahlen +
                ", superZahl=" + superZahl +
                ", rangeSuperZahl=" + rangeSuperZahl +
                '}';
    }
}
