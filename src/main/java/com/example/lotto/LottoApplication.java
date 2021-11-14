package com.example.lotto;

import com.example.lotto.controller.ScannerController;
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
    private ScannerController cotroller;

    public static void main(String[] args) {
        LOGGER.info("Starting to run...");
        SpringApplication.run(LottoApplication.class, args).close();
        System.out.println(" ^^ Viel Spaß und Viel Glück ^^ ");
        LOGGER.info("Completed the run...");
    }

    /**
     * Ruf die Aplikation auf
     *
     * @param args wird nicht analysiert
     */
    @Override
    public void run(String... args) throws Exception {
        try {
            cotroller.startGame();
        } catch (Exception ex) {
            LOGGER.error("Error in run : {}", ex.getMessage());
        }

    }
}
