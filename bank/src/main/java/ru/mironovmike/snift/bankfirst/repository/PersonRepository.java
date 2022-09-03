package ru.mironovmike.snift.bankfirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mironovmike.snift.bankfirst.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person getByUsername(String username);
}
