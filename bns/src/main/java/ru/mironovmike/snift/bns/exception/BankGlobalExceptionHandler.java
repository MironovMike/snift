package ru.mironovmike.snift.bns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BankIncorrectData> handleException(NoSuchBankException exception) {
        BankIncorrectData data = new BankIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
