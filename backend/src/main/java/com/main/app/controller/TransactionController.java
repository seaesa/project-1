package com.main.app.controller;

import com.main.app.dto.TransactionDTO;
import com.main.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Integer id) {
        TransactionDTO transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/account/{accID}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Integer accID) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(accID);
        return ResponseEntity.ok(transactions);
    }
    
    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionDTO.setTransType(1); // 1 = Deposit
            TransactionDTO result = transactionService.performTransaction(transactionDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionDTO.setTransType(2); // 2 = Withdraw
            TransactionDTO result = transactionService.performTransaction(transactionDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<TransactionDTO> performTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            TransactionDTO result = transactionService.performTransaction(transactionDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 