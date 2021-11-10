package com.example.lotto;

import com.example.lotto.controller.ScannerCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LottoApplication implements CommandLineRunner {
    @Autowired
    private ScannerCotroller cotroller;

    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);


    }

    @Override
    public void run(String... args)  {
        cotroller.startGame();
    }
}
