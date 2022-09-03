package ru.mironovmike.snift.bankfirst.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.mironovmike.snift.bankfirst.entity.Bank;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankRestClientImplTest {
    private static final String BASE_URL = "http://localhost:8080";

    private final WebClient webClient = WebClient.create(BASE_URL);

    private final BankRestClient bankRestClient = new BankRestClientImpl(webClient);

    @Test
    void retrieveAllBanks() {
        List<Bank> banks = bankRestClient.retrieveAllBanks();
        assertTrue(banks.size() > 0);
    }

    @Test
    void retrieveBank() {
        Bank bank = bankRestClient.retrieveBank(1);
        System.out.println(bank);
        assertNotNull(bank);
    }

    @Test
    void retrieveBank_notFound() {
        assertThrows(WebClientResponseException.class, () -> bankRestClient.retrieveBank(999));
    }

    @Test
    void retrieveBanksByTitle() {
        assertTrue(bankRestClient.retrieveBanksByTitle("some bank").size() > 0);
    }
}