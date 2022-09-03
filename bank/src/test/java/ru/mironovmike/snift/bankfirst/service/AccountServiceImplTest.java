package ru.mironovmike.snift.bankfirst.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mironovmike.snift.bankfirst.entity.Account;
import ru.mironovmike.snift.bankfirst.entity.Person;
import ru.mironovmike.snift.bankfirst.entity.Role;
import ru.mironovmike.snift.bankfirst.exception.NoSuchAccountException;
import ru.mironovmike.snift.bankfirst.repository.AccountRepository;
import ru.mironovmike.snift.bankfirst.repository.PersonRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    @Test
    void loadUserByUsername_successful_user_load() {
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        Set<Role> roles = new HashSet<>(Collections.singletonList(Role.builder().name("client").build()));
        when(personRepository.getByUsername("mike")).thenReturn(Person.builder()
                .username("mike")
                .password("123123")
                .roles(roles)
                .build());
        AccountServiceImpl accountService = new AccountServiceImpl(accountRepository, personRepository);

        UserDetails userDetails = accountService.loadUserByUsername("mike");
        assertEquals("mike", userDetails.getUsername());
    }

    @Test
    void getById_successful_get() {
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(Account.builder().id(1).build()));
        AccountServiceImpl accountService = new AccountServiceImpl(accountRepository, personRepository);
        Account account = accountService.getById(1);
        assertEquals(1, account.getId());
    }

    @Test
    void getById_no_such_account_exception() {
        AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        AccountServiceImpl accountService = new AccountServiceImpl(accountRepository, personRepository);
        assertThrows(NoSuchAccountException.class, () -> accountService.getById(1));
    }

    @Test
    void getAccountsByPersonId() {
    }
}