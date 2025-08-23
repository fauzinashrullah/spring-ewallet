package com.fauzi.ewallet.wallet.application.usecase;

import java.util.UUID;

public interface WalletUseCase {
    void createWallet(UUID userId);
    String myWallet();

}
