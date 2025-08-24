package com.fauzi.ewallet.wallet.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fauzi.ewallet.shared.exception.NotFoundException;
import com.fauzi.ewallet.shared.security.UserDetailsImpl;
import com.fauzi.ewallet.wallet.application.dto.WalletResult;
import com.fauzi.ewallet.wallet.application.usecase.WalletUseCase;
import com.fauzi.ewallet.wallet.domain.model.Wallet;
import com.fauzi.ewallet.wallet.domain.reposittory.WalletRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService implements WalletUseCase{
    
    private final WalletRepository repository;

    @Override
    public void createWallet(UUID userId){
        Wallet newWallet = new Wallet(UUID.randomUUID(), userId, 0);
        repository.save(newWallet);
    }

    @Override
    public String myWallet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        Wallet wallet = repository.findByUserId(userId)
            .orElseThrow(() -> new NotFoundException("Wallet not found"));
        
        return String.valueOf(wallet.getAmount());
    }

    @Override
    public List<WalletResult> allWallet(){
        return repository.findAll()
            .stream()
            .map(e -> new WalletResult(e.getUserId(), String.valueOf(e.getAmount())))
            .toList();
    }
}
