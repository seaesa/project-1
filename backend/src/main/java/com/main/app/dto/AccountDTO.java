package com.main.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer accID;
    private String customerName;
    private String email;
    private String phone;
    private Double balance;
} 