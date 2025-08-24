package com.fauzi.ewallet.wallet.application.usecase;

import java.util.List;
import java.util.UUID;

import com.fauzi.ewallet.wallet.application.dto.WalletResult;

public interface WalletUseCase {
    void createWallet(UUID userId);
    String myWallet();
    List<WalletResult> allWallet();
}
