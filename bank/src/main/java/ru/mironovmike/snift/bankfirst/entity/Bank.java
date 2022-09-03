package ru.mironovmike.snift.bankfirst.entity;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Bank {
    private long id;

    @NotNull
    private String bik;

    @NotNull
    private String title;

    @NotNull
    private String snift_number;

    @Pattern(regexp = "[A-Z]{2}")
    @NotNull
    private String country;
}
