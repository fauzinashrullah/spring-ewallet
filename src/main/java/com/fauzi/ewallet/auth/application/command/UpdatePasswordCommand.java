package com.fauzi.ewallet.auth.application.command;

public record UpdatePasswordCommand(String oldPassword, String newPassword) {}
