package com.main.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccID")
    private Integer accID;
    
    @Column(name = "CustomerName", nullable = false)
    private String customerName;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Phone")
    private String phone;
    
    @Column(name = "Balance")
    private Double balance;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionDetails> transactions;
} 