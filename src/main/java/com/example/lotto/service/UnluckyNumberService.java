package com.example.lotto.service;

import com.example.lotto.model.UnluckyNumbers;
import com.example.lotto.repository.UnluckyNumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnluckyNumberService {

    @Autowired
    private UnluckyNumbersRepository unluckyNumbersRepository;

    public void addUnluckyNumber(UnluckyNumbers unluckyNumbers){
        unluckyNumbersRepository.save(unluckyNumbers);
    }

    public List<UnluckyNumbers> getAllUnluckyNumbers(){
        return unluckyNumbersRepository.findAll();
    }

    public List<UnluckyNumbers> getLastUnluckyNumbers(){

        return unluckyNumbersRepository.findAll();
    }
    public void deleteUnluckyNumbers(){

     unluckyNumbersRepository.deleteAll();
    }

}