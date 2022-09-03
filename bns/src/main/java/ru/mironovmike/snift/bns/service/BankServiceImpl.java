package ru.mironovmike.snift.bns.service;

import org.springframework.stereotype.Service;
import ru.mironovmike.snift.bns.entity.Bank;
import ru.mironovmike.snift.bns.exception.NoSuchBankException;
import ru.mironovmike.snift.bns.repository.BankRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getBankById(long id) {
        Optional<Bank> optional = bankRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NoSuchBankException("Bank with id = " + id + " does not exists.");
    }

    @Override
    public List<Bank> getBanksByTitle(String title) {
        return bankRepository.findBanksByTitleEquals(title);
    }
}
