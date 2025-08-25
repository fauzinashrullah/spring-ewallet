package com.fauzi.ewallet.transaction.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, UUID>{
    
}
