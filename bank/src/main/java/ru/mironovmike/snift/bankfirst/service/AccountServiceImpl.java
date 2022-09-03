package ru.mironovmike.snift.bankfirst.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mironovmike.snift.bankfirst.entity.Account;
import ru.mironovmike.snift.bankfirst.entity.Person;
import ru.mironovmike.snift.bankfirst.exception.NoSuchAccountException;
import ru.mironovmike.snift.bankfirst.repository.AccountRepository;
import ru.mironovmike.snift.bankfirst.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService, UserDetailsService {
    private final AccountRepository accountRepository;

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.getByUsername(username);
        if (person == null) {
            log.error("Пользователь {} не найден", username);
            throw new UsernameNotFoundException("Пользователь не найден");
        } else {
            log.info("Пользователь {} найден", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        person.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(person.getUsername(), person.getPassword(), authorities);
    }

    @Override
    public Account getById(long id) {
        return accountRepository.findById(id).orElseThrow(NoSuchAccountException::new);
    }

    @Override
    public List<Account> getAccountsByPersonId(long personId) {
        return accountRepository.findAccountsByPersonId(personId);
    }
}
