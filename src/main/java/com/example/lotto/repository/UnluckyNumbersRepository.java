package com.example.lotto.repository;

import com.example.lotto.model.UnluckyNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UnluckyNumbersRepository extends JpaRepository<UnluckyNumbers,Long> {

    UnluckyNumbers findTopByOrderByIdDesc();

    @Transactional
    @Modifying
    @Query("update unlucky u set u.unluckyNumbers=?1 where u.id=?2")
    int modifyUnluckyNumbers(String unluckyNumbers, int Id);

}