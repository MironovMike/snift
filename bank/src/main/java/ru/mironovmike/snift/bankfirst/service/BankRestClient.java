package ru.mironovmike.snift.bankfirst.service;

import ru.mironovmike.snift.bankfirst.entity.Bank;

import java.util.List;

public interface BankRestClient {
    List<Bank> retrieveAllBanks();

    Bank retrieveBank(long id);

    List<Bank> retrieveBanksByTitle(String title);
}
