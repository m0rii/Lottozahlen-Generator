package com.example.lotto.service;

import com.example.lotto.model.UnluckyNumbers;
import com.example.lotto.repository.UnluckyNumbersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * UnluckyNumberService dient zum Verwalten UnluckyNumberRepository
 */
@Service
public class UnluckyNumberService  {

    @Autowired
    private UnluckyNumbersRepository unluckyNumbersRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void addUnluckyNumber(UnluckyNumbers unluckyNumbers) {
        logger.info("insert data into database -> {}" , unluckyNumbers);
        unluckyNumbersRepository.save(unluckyNumbers);
    }

    public List<UnluckyNumbers> getAllUnluckyNumbers() {
        logger.info("get all unlucky numbers");
        return unluckyNumbersRepository.findAll();
    }

    public UnluckyNumbers getLastUnluckyNumbers() {
        UnluckyNumbers lastUnluckyNumbers = null;
        try {
            lastUnluckyNumbers = unluckyNumbersRepository.findTopByOrderByIdDesc();
            logger.info("read last unlucky number from database");
        } catch (NoSuchElementException e) {
            logger.error("could not read last unluckynumber from database " + e);

        }
        return lastUnluckyNumbers;
    }

    public void deleteAllUnluckyNumbers() {
        logger.info("delete all unlucky numbers");
        unluckyNumbersRepository.deleteAll();

    }

    public void updateUnluckyNumbers(UnluckyNumbers unluckyNumbers) {
        logger.info("update {} in database", unluckyNumbers);
        unluckyNumbersRepository.save(unluckyNumbers);
    }

    public void deleteLastUnluckyNumbers(Long id) {
        logger.info("delete last unlucky number for id = {} in database", id);
        unluckyNumbersRepository.deleteById(id);
    }

}