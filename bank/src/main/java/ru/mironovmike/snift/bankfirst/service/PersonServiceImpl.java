package ru.mironovmike.snift.bankfirst.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mironovmike.snift.bankfirst.entity.Account;
import ru.mironovmike.snift.bankfirst.entity.Person;
import ru.mironovmike.snift.bankfirst.entity.Role;
import ru.mironovmike.snift.bankfirst.exception.NoSuchAccountException;
import ru.mironovmike.snift.bankfirst.exception.NoSuchPersonException;
import ru.mironovmike.snift.bankfirst.repository.AccountRepository;
import ru.mironovmike.snift.bankfirst.repository.PersonRepository;
import ru.mironovmike.snift.bankfirst.repository.RoleRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Person savePerson(Person person) {
        log.info("Saving new person");
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToPerson(long personId, String roleName) {
        Role role = roleRepository.findByName(roleName);
        personRepository.findById(personId).ifPresent(person -> person.getRoles().add(role));
    }

    @Override
    public void addAccountToPerson(long personId, long accountId) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(NoSuchAccountException::new);
        Person person = personRepository
                .findById(personId)
                .orElseThrow(NoSuchPersonException::new);
        account.setPerson(person);
        person.addAccount(account);
    }

    @Override
    public Person getPerson(long personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        return optionalPerson.orElse(null);
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getByUsername(String username) {
        return personRepository.getByUsername(username);
    }
}
