package com.main.app.service;

import com.main.app.dto.TransactionDTO;
import com.main.app.entity.TransactionDetails;
import com.main.app.entity.Account;
import com.main.app.repository.TransactionRepository;
import com.main.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private AccountRepository accountRepository; 
    
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<TransactionDTO> getTransactionsByAccountId(Integer accID) {
        return transactionRepository.findByAccount_AccIDOrderByDateOfTransDesc(accID).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public TransactionDTO performTransaction(TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccID()).orElse(null);
        if (account == null) {
            return null;
        }
        
        Double currentBalance = account.getBalance();
        Double transactionAmount = transactionDTO.getTransMoney();
        Integer transactionType = transactionDTO.getTransType();
        
        // 1 = Deposit, 2 = Withdraw
        if (transactionType == 1) { // Deposit
            account.setBalance(currentBalance + transactionAmount);
        } else if (transactionType == 2) { // Withdraw
            if (currentBalance < transactionAmount) {
                throw new RuntimeException("Insufficient balance");
            }
            account.setBalance(currentBalance - transactionAmount);
        } else {
            throw new RuntimeException("Invalid transaction type");
        }
        
        // Save updated account
        accountRepository.save(account);
        
        // Create transaction record
        TransactionDetails transaction = new TransactionDetails();
        transaction.setAccount(account);
        transaction.setTransMoney(transactionAmount);
        transaction.setTransType(transactionType);
        transaction.setDateOfTrans(LocalDate.now());
        
        TransactionDetails savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }
    
    public TransactionDTO getTransactionById(Integer id) {
        TransactionDetails transaction = transactionRepository.findById(id).orElse(null);
        return transaction != null ? convertToDTO(transaction) : null;
    }
    
    private TransactionDTO convertToDTO(TransactionDetails transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransID(transaction.getTransID());
        dto.setAccID(transaction.getAccount().getAccID());
        dto.setTransMoney(transaction.getTransMoney());
        dto.setTransType(transaction.getTransType());
        dto.setDateOfTrans(transaction.getDateOfTrans());
        return dto;
    }
} 