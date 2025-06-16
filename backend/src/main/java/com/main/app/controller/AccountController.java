package com.main.app.controller;

import com.main.app.dto.AccountDTO;
import com.main.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Integer id) {
        AccountDTO account = accountService.getAccountById(id);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO savedAccount = accountService.saveAccount(accountDTO);
        return ResponseEntity.ok(savedAccount);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Integer id, @RequestBody AccountDTO accountDTO) {
        AccountDTO updatedAccount = accountService.updateAccount(id, accountDTO);
        if (updatedAccount != null) {
            return ResponseEntity.ok(updatedAccount);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        boolean deleted = accountService.deleteAccount(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
} 