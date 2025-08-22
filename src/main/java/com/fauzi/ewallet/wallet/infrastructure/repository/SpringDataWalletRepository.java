package com.fauzi.ewallet.wallet.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fauzi.ewallet.wallet.infrastructure.persistence.WalletEntity;

public interface SpringDataWalletRepository extends JpaRepository<WalletEntity, UUID>{
    
}
