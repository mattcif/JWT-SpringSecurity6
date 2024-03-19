package com.mattcif.security.auth;

import com.mattcif.security.user.UserRepository;
import com.mattcif.security.validation.EmailValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(
             @RequestBody @Valid RegisterRequest request
    ) {

        if (request.getFirstname().isEmpty() || request.getLastname().isEmpty() ) {
            return ResponseEntity.badRequest().body("Nome e Sobrenome não devem ser nulos");
        }

        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.isValid(request.getEmail())) {
            return ResponseEntity.badRequest().body("email inválido");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("email já cadastrado");
        }

        if (request.getPassword().length() < 8 || request.getPassword().length() > 30) {
            return ResponseEntity.badRequest()
                    .body("senha inválida! a senha deve ser maior que 8 caracteres e menor que 30");
        }

        return ResponseEntity.ok(service.register(request).toString());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
