package com.example.lotto.service;

import com.example.lotto.model.UnluckyNumbers;
import com.example.lotto.repository.UnluckyNumbersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnluckyNumberServiceTest {
    @InjectMocks
    UnluckyNumberService service;

    @Mock
    UnluckyNumbersRepository dao;


    @Test
    public void should_find_no_unluckyNumbers_if_repository_is_empty() {
        Iterable<UnluckyNumbers> unluckyNumbers = service.getAllUnluckyNumbers();
        assertThat(unluckyNumbers).isEmpty();
    }

    @Test
    void addUnluckyNumber() {
        UnluckyNumbers unlucky1 = new UnluckyNumbers("10,20,30");
        service.addUnluckyNumber(unlucky1);
        verify(dao, times(1)).save(unlucky1);

    }

    @Test
    void getAllUnluckyNumbers() {
        List<UnluckyNumbers> list = new ArrayList<UnluckyNumbers>();
        UnluckyNumbers unlucky1 = new UnluckyNumbers("10,20,30");
        UnluckyNumbers unlucky2 = new UnluckyNumbers("11,21,31");
        UnluckyNumbers unlucky3 = new UnluckyNumbers("5,15,45");
        list.add(unlucky1);
        list.add(unlucky2);
        list.add(unlucky3);
        when(dao.findAll()).thenReturn(list);

        List<UnluckyNumbers> empList = service.getAllUnluckyNumbers();
        assertEquals(3, empList.size());

        verify(dao, times(1)).findAll();
        assertEquals("10,20,30", unlucky1.getUnluckyNumbers());
        assertEquals(list.get(1).getUnluckyNumbers(), unlucky2.getUnluckyNumbers());
        assertEquals(list.get(1).getCreateDate(), unlucky3.getCreateDate());
    }

    @Test
    void getLastUnluckyNumbers() {
        List<UnluckyNumbers> list = new ArrayList<UnluckyNumbers>();
        UnluckyNumbers unlucky1 = new UnluckyNumbers("10,20,30");
        list.add(unlucky1);
        when(dao.findTopByOrderByIdDesc()).thenReturn(unlucky1);
        UnluckyNumbers lastNumbers = service.getLastUnluckyNumbers();
        assertEquals(unlucky1.getId(), lastNumbers.getId());

    }

    @Test
    void deleteAllUnluckyNumbers() {
        UnluckyNumbers unlucky1 = new UnluckyNumbers("10,20,30");
        UnluckyNumbers unlucky2 = new UnluckyNumbers("1,40,22,34,42");
        UnluckyNumbers unlucky3 = new UnluckyNumbers("12,2,10,30,40");
        service.addUnluckyNumber(unlucky1);
        service.addUnluckyNumber(unlucky2);
        service.addUnluckyNumber(unlucky3);
        service.deleteAllUnluckyNumbers();
        verify(dao, times(1)).deleteAll();

    }

    @Test
    void updateUnluckyNumbers() {
        UnluckyNumbers original = new UnluckyNumbers("10,20,30");
        service.addUnluckyNumber(original);

        UnluckyNumbers update = new UnluckyNumbers("1,40,22,34,42");
        service.updateUnluckyNumbers(update);
        verify(dao, times(1)).save(update);
    }

    @Test
    void deleteLastUnluckyNumbers() {
        UnluckyNumbers unlucky1 = new UnluckyNumbers("10,20,30");
        service.addUnluckyNumber(unlucky1);
        service.deleteLastUnluckyNumbers(unlucky1.getId());
        verify(dao, times(1)).deleteById(unlucky1.getId());
    }


}