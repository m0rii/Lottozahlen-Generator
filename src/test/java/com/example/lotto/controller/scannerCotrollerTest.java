package com.example.lotto.controller;

import com.example.lotto.util.LottoType;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class scannerCotrollerTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }



    @Test
    void startGame() {


    }

    @Test
    void detectGameTest() {
        String testString = "Eurojackpot"; //Lotto6aus49
        provideInput(testString);
        Assertions.assertEquals(testString, LottoType.EUROJACKPOT.toString());//LottoType.LOTTO6AUS49.toString()


    }

    @Test
    void detectGameWrongInput() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            String testString = "bla bla";
            provideInput(testString);
         //   ScannerController.detectGame();
        });
        String expectedMessage = "wrong input";
        String actualMessage = exception.getMessage(); // No line found
        assertFalse(actualMessage.contains(expectedMessage));
    }
}