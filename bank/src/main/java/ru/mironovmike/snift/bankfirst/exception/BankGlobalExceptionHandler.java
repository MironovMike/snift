package ru.mironovmike.snift.bankfirst.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BankIncorrectData> handleException(NoSuchAccountException e) {
        String defaultMessage = "Аккаунт не найден";
        BankIncorrectData data = new BankIncorrectData();
        String message = (e.getMessage() != null) ? e.getMessage() : defaultMessage;
        data.setInfo(message);
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
