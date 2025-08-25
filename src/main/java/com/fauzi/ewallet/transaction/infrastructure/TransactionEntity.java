package com.fauzi.ewallet.transaction.infrastructure;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TransactionEntity {
    @Id
    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private int amount;
    private String message;
}
