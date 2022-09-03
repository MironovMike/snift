package ru.mironovmike.snift.bankfirst;

import lombok.*;

import javax.validation.constraints.*;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "bank")
@Validated
@Setter
@Getter
public class AppProperties {

    @NotBlank
    private String title;

    @Pattern(regexp = "[A-Z]{2}")
    private String country;
}
