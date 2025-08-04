package com.fauzi.ewallet.auth.application.inbound.dto.command;

public record UpdatePasswordCommand(String oldPassword, String newPassword) {}
