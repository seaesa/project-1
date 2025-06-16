package com.main.app.repository;

import com.main.app.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, Integer> {
    List<TransactionDetails> findByAccount_AccIDOrderByDateOfTransDesc(Integer accID);
} 