package com.example.lotto.util;

/**
 *  Die Lotto Spiel Typen
 */
public enum LottoType {
    LOTTO6AUS49("Lotto6aus49"),
    EUROJACKPOT("Eurojackpot");

    private final String name;

    LottoType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean equalValue(String name){
        return this.name.equals(name);
    }
}
