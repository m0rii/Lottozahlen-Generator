package com.example.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static com.example.lotto.controller.ScannerCotroller.startGame;

@SpringBootApplication
public class LottoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);
       startGame();
    }

}
