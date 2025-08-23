package com.fauzi.ewallet.wallet.domain.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Wallet {
    private UUID id;
    private UUID userId;
    private int amount = 0;
}
