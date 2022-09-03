package ru.mironovmike.snift.bns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mironovmike.snift.bns.entity.Bank;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    List<Bank> findBanksByTitleEquals(String title);
}
