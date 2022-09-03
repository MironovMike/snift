package ru.mironovmike.snift.bns.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String bik;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private String snift_number;

    @Column
    @NotNull
    @Pattern(regexp = "[A-Z]{2}")
    private String country;
}
