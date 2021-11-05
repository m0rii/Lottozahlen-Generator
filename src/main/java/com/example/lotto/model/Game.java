package com.example.lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements Generator {
    private int rangeTippZahlen;
    private int tippZahlen;
    private int superZahl;
    private int rangeSuperZahl;

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

    @Override
    public List<Integer> randomTipp() {
        List<Integer> tipps = new ArrayList<>();
        for (int i = 0; i < this.getTippZahlen(); i++) {
            tipps.add((int) (Math.random() * this.getRangeTippZahlen() + 1));
        }
        Collections.sort(tipps);
        return tipps;
    }

    @Override
    public ArrayList<Integer> randomSuperZahlTipp() {
        ArrayList<Integer> superZahlTipp = new ArrayList<>();
        for (int i = 0; i < this.getSuperZahl(); i++) {
            superZahlTipp.add((int) (Math.random() * this.getRangeSuperZahl() + 0));
        }
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
