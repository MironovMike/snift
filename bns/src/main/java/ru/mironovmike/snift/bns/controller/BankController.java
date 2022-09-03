package ru.mironovmike.snift.bns.controller;

import org.springframework.web.bind.annotation.*;
import ru.mironovmike.snift.bns.entity.Bank;
import ru.mironovmike.snift.bns.service.BankService;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping()
    private List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{id}")
    private Bank getBankById(@PathVariable long id) {
        return bankService.getBankById(id);
    }

    @GetMapping("/bankTitle")
    private List<Bank> getBanksByTitle(@RequestParam String title) {
        return bankService.getBanksByTitle(title);
    }
}
