package com.main.app.service;

import com.main.app.dto.AccountDTO;
import com.main.app.entity.Account;
import com.main.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public AccountDTO getAccountById(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        return account != null ? convertToDTO(account) : null;
    }
    
    public AccountDTO saveAccount(AccountDTO accountDTO) {
        Account account = convertToEntity(accountDTO);
        if (account.getBalance() == null) {
            account.setBalance(0.0);
        }
        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
    }
    
    public AccountDTO updateAccount(Integer id, AccountDTO accountDTO) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            account.setCustomerName(accountDTO.getCustomerName());
            account.setEmail(accountDTO.getEmail());
            account.setPhone(accountDTO.getPhone());
            Account updatedAccount = accountRepository.save(account);
            return convertToDTO(updatedAccount);
        }
        return null;
    }
    
    public boolean deleteAccount(Integer id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public boolean updateBalance(Integer id, Double newBalance) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            account.setBalance(newBalance);
            accountRepository.save(account);
            return true;
        }
        return false;
    }
    
    private AccountDTO convertToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccID(account.getAccID());
        dto.setCustomerName(account.getCustomerName());
        dto.setEmail(account.getEmail());
        dto.setPhone(account.getPhone());
        dto.setBalance(account.getBalance());
        return dto;
    }
    
    private Account convertToEntity(AccountDTO dto) {
        Account account = new Account();
        account.setAccID(dto.getAccID());
        account.setCustomerName(dto.getCustomerName());
        account.setEmail(dto.getEmail());
        account.setPhone(dto.getPhone());
        account.setBalance(dto.getBalance());
        return account;
    }
} 