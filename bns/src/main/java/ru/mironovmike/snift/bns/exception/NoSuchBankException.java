package ru.mironovmike.snift.bns.exception;

public class NoSuchBankException extends RuntimeException {
    public NoSuchBankException(String message) {
        super(message);
    }
}
