package com.main.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "transaction_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransID")
    private Integer transID;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccID", nullable = false)
    private Account account;
    
    @Column(name = "TransMoney")
    private Double transMoney;
    
    @Column(name = "TransType")
    private Integer transType; // 1 = Deposit, 2 = Withdraw
    
    @Column(name = "DateOfTrans")
    private LocalDate dateOfTrans;
} 