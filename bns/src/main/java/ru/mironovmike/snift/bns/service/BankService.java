package ru.mironovmike.snift.bns.service;

import ru.mironovmike.snift.bns.entity.Bank;

import java.util.List;

public interface BankService {
    List<Bank> getAllBanks();

    Bank getBankById(long id);

    List<Bank> getBanksByTitle(String title);
}
