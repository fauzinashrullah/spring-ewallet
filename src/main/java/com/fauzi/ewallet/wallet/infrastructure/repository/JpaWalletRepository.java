package com.fauzi.ewallet.wallet.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fauzi.ewallet.wallet.domain.model.Wallet;
import com.fauzi.ewallet.wallet.domain.reposittory.WalletRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaWalletRepository implements WalletRepository{
    
    private final SpringDataWalletRepository walletRepository;

    @Override
    public Optional<Wallet> findByUserId(UUID userId){
        return walletRepository.findById(userId).map(e ->  new Wallet(e.getId(), e.getUserId(), e.getAmount()));
    }
}
