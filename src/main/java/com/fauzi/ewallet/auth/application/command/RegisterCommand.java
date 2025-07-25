package com.fauzi.ewallet.auth.application.command;

public record RegisterCommand(String name, String username, String phoneNumber, String email, String password) {}
