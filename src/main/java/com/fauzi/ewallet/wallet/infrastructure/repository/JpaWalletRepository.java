package com.fauzi.ewallet.wallet.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fauzi.ewallet.wallet.domain.model.Wallet;
import com.fauzi.ewallet.wallet.domain.reposittory.WalletRepository;
import com.fauzi.ewallet.wallet.infrastructure.persistence.WalletEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaWalletRepository implements WalletRepository{
    
    private final SpringDataWalletRepository walletRepository;

    @Override
    public Optional<Wallet> findByUserId(UUID userId){
        return walletRepository.findByUserId(userId).map(e ->  new Wallet(e.getId(), e.getUserId(), e.getAmount()));
    }

    @Override
    public void save (Wallet w){
        WalletEntity e = new WalletEntity();
        e.setId(w.getId());
        e.setUserId(w.getUserId());
        e.setAmount(w.getAmount());
        walletRepository.save(e);
    }
}
