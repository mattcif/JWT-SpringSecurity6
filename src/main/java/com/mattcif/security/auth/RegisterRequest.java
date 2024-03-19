package com.mattcif.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    @NotNull
    private String firstname;
    @NotNull
    private String lastname;

    @Email
    @NotEmpty(message = "não deve ser vazio")
    private String email;

    @Size(min = 8, max = 20)
    private String password;
}
