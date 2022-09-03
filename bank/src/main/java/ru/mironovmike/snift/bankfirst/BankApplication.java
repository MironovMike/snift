package ru.mironovmike.snift.bankfirst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mironovmike.snift.bankfirst.entity.Account;
import ru.mironovmike.snift.bankfirst.entity.Currency;
import ru.mironovmike.snift.bankfirst.entity.Person;
import ru.mironovmike.snift.bankfirst.entity.Role;
import ru.mironovmike.snift.bankfirst.repository.AccountRepository;
import ru.mironovmike.snift.bankfirst.service.PersonService;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
public class BankApplication {

    final AppProperties appProperties;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public BankApplication(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Bean
    CommandLineRunner run(PersonService personService) {
        return args -> {

            Person person = personService.savePerson(
                    Person.builder()
                            .username("mike")
                            .password("123123") // for study purpose
                            .firstName("Mike")
                            .secondName("Mironov")
                            .build());

            Role role = personService.saveRole(
                    Role.builder()
                            .name("client")
                            .build());

            Account account = Account.builder()
                    .number("4545.5656")
                    .actualBalance(100)
                    .availableBalance(50)
                    .currency(Currency.RUB)
                    .build();
            Account account2 = Account.builder()
                    .number("4545.5657")
                    .actualBalance(80)
                    .availableBalance(70)
                    .currency(Currency.RUB)
                    .build();
            personService.addAccountToPerson(person.getId(), accountRepository.save(account).getId());
            personService.addAccountToPerson(person.getId(), accountRepository.save(account2).getId());

            personService.addRoleToPerson(person.getId(), role.getName());
            //personService.getByUsername("mike");
//            Person person = Person.builder()
//                    .firstName("Mike")
//                    .secondName("Mironov")
//                    .build();
//            Account account = Account.builder()
//                    .person(person)
//                    .number("4545.5656")
//                    .actualBalance(100)
//                    .availableBalance(50)
//                    .currency(Currency.RUB)
//                    .build();
//            Account account2 = Account.builder()
//                    .person(person)
//                    .number("4545.5657")
//                    .actualBalance(80)
//                    .availableBalance(70)
//                    .currency(Currency.RUB)
//                    .build();
//
//            person.addAccount(account);
//            person.addAccount(account2);
//            personRepository.save(person);

//            Role role = Role.builder().name("client").build();
//            roleRepository.save(role);
//
//            personService.addRoleToPerson(person.getId(), "client");
        };
    }
}
