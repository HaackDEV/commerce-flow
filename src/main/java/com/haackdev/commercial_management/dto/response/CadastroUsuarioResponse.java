package com.haackdev.commercial_management.dto.response;

public record CadastroUsuarioResponse(
        Long id,
        String nome,
        String email
) {
}
