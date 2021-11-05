package com.example.lotto.util;

public enum LottoType {
    LOTTO6AUS49("Lotto6aus49"),
    EUROJACKPOT("Eurojackpot");

    private final String name;

    private LottoType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
