package com.haackdev.commercial_management.resource;

import com.haackdev.commercial_management.dto.request.CadastroUsuarioRequest;
import com.haackdev.commercial_management.dto.response.CadastroUsuarioResponse;
import com.haackdev.commercial_management.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuarios")
public class UsuarioResource {

    private final UsuarioService usuarioService;

    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Realiza cadastro do usuário", description = "Realiza cadastro com email, nome e senha fornecidos")
    @PostMapping
    public ResponseEntity<CadastroUsuarioResponse> cadastrar(@RequestBody @Valid CadastroUsuarioRequest request) {
        CadastroUsuarioResponse response = usuarioService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
