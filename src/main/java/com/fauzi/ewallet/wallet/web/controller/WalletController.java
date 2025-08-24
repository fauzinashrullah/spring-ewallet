package com.fauzi.ewallet.wallet.web.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fauzi.ewallet.shared.common.dto.ApiResponse;
import com.fauzi.ewallet.wallet.application.usecase.WalletUseCase;
import com.fauzi.ewallet.wallet.web.dto.WalletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/wallet")
@RequiredArgsConstructor
public class WalletController {
    
    private final WalletUseCase useCase;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<WalletResponse >> myWallet() {
        WalletResponse response = new WalletResponse(useCase.myWallet());
        return ResponseEntity.ok(new ApiResponse<>(true, "Get wallet success", response));
    }
    
    
}
