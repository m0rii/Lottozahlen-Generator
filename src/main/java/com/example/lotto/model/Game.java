package com.example.lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Game Klasse representiert die Spiel und implementiert die tippGenarator Interface
 */
public class Game implements tippGenerator {
    private int rangeTippZahlen;
    private int tippZahlen;
    private int superZahl;
    private int rangeSuperZahl;
    private String gameName;

    /**
     * getRangeTippZahlen liefert dem Bereich von Zahlen zurück.
     *
     * @return Für Looto6au46 liefert 46 und für Eurojackpot 50 zurück.
     */
    public int getRangeTippZahlen() {
        return rangeTippZahlen;
    }

    /**
     * setRangeTippZahlen setzt den Bereich der Zahlen für das Spiel.
     *
     * @param rangeTippZahlen Der Bereich des Spiels
     */
    public void setRangeTippZahlen(int rangeTippZahlen) {
        this.rangeTippZahlen = rangeTippZahlen;
    }

    /**
     * getSuperZahl liefert die Anzahl der Superzahlen zurück.
     *
     * @return die Anzahl der Superzahlen
     */
    public int getSuperZahl() {
        return superZahl;
    }

    /**
     * setSuperZahl setzt die Anzahl der Superzahlen.
     *
     * @param superZahl die Anzahl der Superzahlen
     */
    public void setSuperZahl(int superZahl) {
        this.superZahl = superZahl;
    }

    /**
     * getRangeSuperZahl liefert den Bereich der Superzahlen für das Spiel zurück.
     *
     * @return Der Bereich der Superzahlen
     */
    public int getRangeSuperZahl() {
        return rangeSuperZahl;
    }

    /**
     * getTippZahlen liefert die Anzahl der Tippreihe für das Spiel zurück.
     *
     * @return die Anzahl der Tippreihe
     */
    public int getTippZahlen() {
        return tippZahlen;
    }

    /**
     * setRangeSuperZahl setzt der Bereich der Superzahlen für das Spiel
     *
     * @Param rangeSuperZahl der Bereich der Superzahlen
     */
    public void setRangeSuperZahl(int rangeSuperZahl) {
        this.rangeSuperZahl = rangeSuperZahl;
    }

    /**
     * setTippZahlen setzt den Bereich der Tppreihe für das Spiel.
     *
     * @Param tippZahlen der Bereich der Tppreihe
     */
    public void setTippZahlen(int tippZahlen) {
        this.tippZahlen = tippZahlen;
    }

    /**
     * getGameName liefert der Type des Spiles zurück.
     *
     * @return der Type des Spiles
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * setGameName setzt der Type des Spiles.
     *
     * @Param gameName der Type des Spiles
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * randomTipp generiert ein Tippreihe
     *
     * @param unluckyNums ein List von Unglückzahlen, die bei der Gerenrierung der Tippreihe berücksigtiegt wird.
     * @return ein Integer List von generierte Tippreihe
     */
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

    /**
     * randomSuperZahlTipp generiert die Superzahlen
     *
     * @param unluckyNums ein List von Unglückzahlen, die bei der Gerenrierung der Superzahlen berücksigtiegt wird.
     * @return ein Integer List von generierte Superzahlen
     */
    @Override
    public List<Integer> randomSuperZahlTipp(List<Integer> unluckyNums) {
        List<Integer> superZahlTipp = new ArrayList<>();
        do {
            int tmp = (int) (Math.random() * this.getRangeSuperZahl() + 1);
            if (!superZahlTipp.contains(tmp) && !unluckyNums.contains(tmp)) {
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
