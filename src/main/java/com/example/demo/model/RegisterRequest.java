package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @Email(message = "Email is invalid!")
    @NotBlank(message = "Email must not be blank!")
    @Column(unique = true)
    String email;

    @Size(min = 8, message = "Password must be at least 8 characters!")
    @NotBlank(message = "Password must not be blank!")
    String password;

    @NotBlank(message = "Phone must not be blank!")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Phone is invalid!")
    @Column(unique = true)
    String phone;

}
