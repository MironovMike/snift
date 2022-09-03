package ru.mironovmike.snift.bankfirst.service;

import ru.mironovmike.snift.bankfirst.entity.Account;
import ru.mironovmike.snift.bankfirst.entity.Person;
import ru.mironovmike.snift.bankfirst.entity.Role;

import java.util.List;

public interface PersonService {
    Person savePerson(Person person);

    Role saveRole(Role role);

    void addRoleToPerson(long personId, String roleName);

    void addAccountToPerson(long personId, long accountId);

    Person getPerson(long personId);

    List<Person> getPersons();

    Person getByUsername(String username);
}
