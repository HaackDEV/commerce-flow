package com.haackdev.commercial_management.dto.response;

public record ClienteResponse(
    Long id,
    String razaoSocial,
    String nomeFantasia,
    String cnpj,
    String enderecoCompleto,
    String cidade,
    String estado,
    String telefoneGeral,
    String emailGeral,
    String contatoDesenvolvimento,
    String contatoCompras,
    String condicoesPagamento
) {
}
