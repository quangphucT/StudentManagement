package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank(message = "Name must not be blank!")
    String name;

    @NotBlank(message = "Student code must not be blank!")
    @Pattern(regexp = "SE\\d{6}", message = "Student code is invalid!")
    @Column(unique = true)
    String studentCode;

    @Min(value=0, message = "Score must be at least 0 characters!")
    @Max(value = 10, message = "Score must not be larger than 10!")
    float score;
}
