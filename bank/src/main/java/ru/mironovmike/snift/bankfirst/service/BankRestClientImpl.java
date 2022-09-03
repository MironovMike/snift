package ru.mironovmike.snift.bankfirst.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mironovmike.snift.bankfirst.entity.Bank;

import java.util.List;

import static ru.mironovmike.snift.bankfirst.constants.BankConstants.*;

public class BankRestClientImpl implements BankRestClient {
    private final WebClient webClient;

    public BankRestClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Bank> retrieveAllBanks() {
        return webClient.get().uri(GET_ALL_BANKS)
                .retrieve()
                .bodyToFlux(Bank.class)
                .collectList()
                .block();
    }

    @Override
    public Bank retrieveBank(long id) {
        return webClient.get().uri(GET_BANK, id)
                .retrieve()
                .bodyToMono(Bank.class)
                .block();
    }

    @Override
    public List<Bank> retrieveBanksByTitle(String title) {
        String uriString = UriComponentsBuilder.fromUriString(GET_BANKS_BY_TITLE)
                .queryParam("title", title)
                .build().toUriString();
        return webClient.get().uri(uriString)
                .retrieve()
                .bodyToFlux(Bank.class)
                .collectList()
                .block();
    }
}
