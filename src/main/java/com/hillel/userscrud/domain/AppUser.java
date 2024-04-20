package com.hillel.userscrud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NotBlank(message = "Name is mandatory")
    String name;

    @NotBlank(message = "Email is mandatory")
    String email;
}
