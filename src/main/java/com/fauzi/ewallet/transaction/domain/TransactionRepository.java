package com.fauzi.ewallet.transaction.domain;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll();
}
