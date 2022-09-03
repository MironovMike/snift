package ru.mironovmike.snift.bankfirst.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.mironovmike.snift.bankfirst.entity.Account;
import ru.mironovmike.snift.bankfirst.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping
    public List<Account> getAccountsByPersonId(@RequestParam long id) {
        return accountService.getAccountsByPersonId(id);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable long id) {
        return accountService.getById(id);
    }
}
