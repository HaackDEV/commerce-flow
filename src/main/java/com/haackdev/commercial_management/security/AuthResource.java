package com.haackdev.commercial_management.security;

import com.haackdev.commercial_management.dto.request.LoginRequest;
import com.haackdev.commercial_management.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoints para autenticação de login")
public class AuthResource {

    private final AuthenticationService authenticationService;

    public AuthResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Realiza login", description = "Realiza login com email e senha fornecidos")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authenticationService.login(request);
    }
}
