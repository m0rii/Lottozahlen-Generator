package com.example.lotto;

import com.example.lotto.controller.ScannerCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LottoApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(LottoApplication.class);
    @Autowired
    private ScannerCotroller cotroller;

    public static void main(String[] args) {
        LOGGER.info("Starting to run...");
        SpringApplication.run(LottoApplication.class, args).close();
        System.out.println("CIAO!!!");
        LOGGER.info("Completed the run...");
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            cotroller.startGame();
        }catch (Exception ex){
            LOGGER.error("Error : {}", ex.getMessage());
        }

    }
}
