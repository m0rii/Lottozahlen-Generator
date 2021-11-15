package com.example.lotto.model;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * UnluckyNumbers repräsentiert die Sqlite Tabele
 */
@Entity(name = "unlucky")
@EqualsAndHashCode
public class UnluckyNumbers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String unluckyNumbers;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createDate;

    public UnluckyNumbers(String unluckyNumbers) {
        this.unluckyNumbers = unluckyNumbers;
    }

    public UnluckyNumbers() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnluckyNumbers() {
        return unluckyNumbers;
    }

    public void setUnluckyNumbers(String unluckyNumbers) {
        this.unluckyNumbers = unluckyNumbers;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Override
    public String toString() {
        return "UnluckyNumbers{" +
                "id=" + id +
                ", unluckyNumbers='" + unluckyNumbers + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }


}