package ru.mironovmike.snift.bankfirst.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    @Column(unique = true)
    @NotNull
    private String number;

    @Column
    @NotNull
    private float actualBalance;

    @Column
    @NotNull
    private float availableBalance;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency currency;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id;
    }
}
