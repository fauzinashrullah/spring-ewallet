package com.fauzi.ewallet.wallet.application.dto;

import java.util.UUID;

public record WalletResult (UUID userId, String walletAmount) {
    
}
