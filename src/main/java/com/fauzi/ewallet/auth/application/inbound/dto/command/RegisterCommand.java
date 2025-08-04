package com.fauzi.ewallet.auth.application.inbound.dto.command;

public record RegisterCommand(String name, String username, String phoneNumber, String email, String password) {}
