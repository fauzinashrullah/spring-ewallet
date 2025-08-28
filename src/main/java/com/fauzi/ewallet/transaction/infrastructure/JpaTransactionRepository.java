package com.fauzi.ewallet.transaction.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fauzi.ewallet.transaction.domain.Transaction;
import com.fauzi.ewallet.transaction.domain.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaTransactionRepository implements TransactionRepository{
    
    private final SpringDataTransactionRepository repository;

    public List<Transaction> findAll(){
        return repository.findAll()
            .stream().map(e -> new Transaction(e.getId(), e.getSenderId(), e.getReceiverId(), e.getAmount(), e.getMessage()))
            .toList();
    }
}
