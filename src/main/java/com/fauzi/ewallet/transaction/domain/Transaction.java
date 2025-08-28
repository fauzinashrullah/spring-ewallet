package com.fauzi.ewallet.transaction.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {
    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private int amount;
    private String message;
}
