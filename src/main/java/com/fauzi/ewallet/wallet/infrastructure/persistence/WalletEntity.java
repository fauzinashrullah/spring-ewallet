package com.fauzi.ewallet.wallet.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "wallet")
public class WalletEntity {
    @Id
    private UUID id;
    private UUID userId;
    private int amount;
}
