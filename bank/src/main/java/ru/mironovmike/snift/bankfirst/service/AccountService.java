package ru.mironovmike.snift.bankfirst.service;

import ru.mironovmike.snift.bankfirst.entity.Account;

import java.util.List;

public interface AccountService {
    Account getById(long id);

    List<Account> getAccountsByPersonId(long personId);
}
