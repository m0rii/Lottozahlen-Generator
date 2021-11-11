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

    public UnluckyNumbers getLastUnluckyNumbers(){
       return unluckyNumbersRepository.findTopByOrderByIdDesc();
    }
    public void deleteAllUnluckyNumbers(){
     unluckyNumbersRepository.deleteAll();
    }
    public int updateUnluckyNumbers(String unluckyNumbers, int Id){
        return unluckyNumbersRepository.modifyUnluckyNumbers(unluckyNumbers,Id );
    }

}