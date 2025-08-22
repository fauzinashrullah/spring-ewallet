package com.fauzi.ewallet.wallet.domain.reposittory;

import java.util.Optional;
import java.util.UUID;

import com.fauzi.ewallet.wallet.domain.model.Wallet;

public interface WalletRepository {
    Optional<Wallet> findByUserId(UUID id);
}
