package com.main.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Integer transID;
    private Integer accID;
    private Double transMoney;
    private Integer transType; // 1 = Deposit, 2 = Withdraw
    private LocalDate dateOfTrans;
} 