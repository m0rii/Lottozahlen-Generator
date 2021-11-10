package com.example.lotto.repository;

import com.example.lotto.model.UnluckyNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnluckyNumbersRepository extends JpaRepository<UnluckyNumbers,Long> {

}